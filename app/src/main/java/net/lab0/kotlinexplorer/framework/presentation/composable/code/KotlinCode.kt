package net.lab0.kotlinexplorer.framework.presentation.composable.code

import androidx.compose.foundation.ScrollableColumn
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyleRange
import androidx.compose.ui.text.subSequence
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import net.lab0.grammar.kotlin.KotlinHighlight
import net.lab0.kotlinexplorer.business.domain.extractHighlightsAndAnnotate
import net.lab0.kotlinexplorer.business.domain.parser.Block
import net.lab0.kotlinexplorer.business.domain.parser.KotlinCodeWithBlanks.Companion.placeholder
import net.lab0.kotlinexplorer.business.domain.parser.KotlinCodeWithBlanksImpl
import net.lab0.kotlinexplorer.framework.ui.theme.sourceCodeFontFamily
import net.lab0.kotlinexplorer.utils.Do


@Suppress("DeprecatedCallableAddReplaceWith")
@Deprecated("Only for previews or tests. Use KotlinCode(AnnotatedString, ...)")
@Composable
fun KotlinCode(
    code: String,
    codeStyle: CodeStyle<KotlinHighlight> = DefaultCodeStyle,
    showLineNumbers: Boolean = false,
    activeHighlight: Int? = null,
) {
  KotlinCode(
      AnnotatedString(code),
      codeStyle,
      showLineNumbers,
      activeHighlight,
  )
}

/**
 * @param activeHighlight If not null, highlights the placeholder(s) at the gicen index.
 */
@Composable
fun KotlinCode(
    code: AnnotatedString,
    codeStyle: CodeStyle<KotlinHighlight> = DefaultCodeStyle,
    showLineNumbers: Boolean = false,
    activeHighlight: Int? = null,
) {
  val lines = code.text.split("\n")

  Surface(
      modifier = Modifier.fillMaxWidth(),
      color = codeStyle.backgroundColor,
      contentColor = codeStyle.foregroundColor
  ) {
    Row {
      if (showLineNumbers) {
        GutterPart(lines = lines, codeStyle)
      }
      CodePart(
          lines = lines,
          code = code,
          codeStyle = codeStyle,
          activeHighlight = activeHighlight,
      )
    }
  }
}

@Composable
private fun GutterPart(
    lines: List<String>,
    codeStyle: CodeStyle<KotlinHighlight>,
) {
  Surface(
      modifier = Modifier.padding(end = 8.dp),
      color = codeStyle.gutterBackgroundColor,
      contentColor = codeStyle.gutterForegroundColor
  ) {
    Column(horizontalAlignment = Alignment.End) {
      lines.forEachIndexed { index, _ ->
        Monospace(text = (index + 1).toString())
      }
    }
  }
}

@Composable
private fun CodePart(
    lines: List<String>,
    code: AnnotatedString,
    codeStyle: CodeStyle<KotlinHighlight> = DefaultCodeStyle,
    activeHighlight: Int? = null,
) {
  Column {
    lines.forEachIndexed { index, line ->
      val realStartIndex =
          lines.take(index).fold(0) { acc, e -> acc + e.length + 1 }

      Row {
        KotlinCodeWithBlanksImpl(line).parse().forEach { block ->
          Do exhaustive when (block) {

            is Block.PlaceholderBlock ->
              Surface(
                  modifier = Modifier
                      .padding(horizontal = 2.dp)
                      .align(Alignment.CenterVertically),
                  shape = MaterialTheme.shapes.small,
                  color = when (activeHighlight) {
                    block.index -> codeStyle.activePlaceholderBackgroundColor
                    else -> codeStyle.placeholderBackgroundColor
                  },
                  contentColor = codeStyle.placeholderForegroundColor,
              ) {
                // placeholder to have the right size
                Text(
                    text = " ... ",
                    fontFamily = sourceCodeFontFamily,
                    fontSize = TextUnit.Companion.Sp(10)
                )
              }

            is Block.CodeBlock -> {

              val subSequence = code.subSequence(
                  realStartIndex + block.range.first,
                  realStartIndex + block.range.last + 1
              )

              Monospace(subSequence)
            }

          }
        }
      }
    }
  }
}

//@Preview
@Composable
fun PreviewKotlinCode_Empty() {
  MaterialTheme {
    val code = AnnotatedString("")

    ScrollableColumn {
      KotlinCode(code, DefaultCodeStyle)
    }
  }
}

//@Preview
@Composable
fun PreviewKotlinCode_NoAnswer() {
  MaterialTheme {
    val code = AnnotatedString("val i = 0")
    ScrollableColumn {
      KotlinCode(code, DefaultCodeStyle)
    }
  }
}

@Preview
@Composable
fun PreviewKotlinCode_println() {
  MaterialTheme {
    val code = AnnotatedString("println(${placeholder()})")
    ScrollableColumn {
      KotlinCode(code, DefaultCodeStyle)
    }
  }
}

//@Preview
@Composable
fun PreviewKotlinCode_NewLine() {
  MaterialTheme {
    val code = AnnotatedString(
        """
          |val i = 0
          |fun foo() {
          |  
          |}
        """.trimMargin()
    )
    ScrollableColumn {
      KotlinCode(code, DefaultCodeStyle)
    }
  }
}

//@Preview
@Composable
fun PreviewKotlinCode_AnswerOnly() {
  MaterialTheme {
    val code = AnnotatedString(
        """
            |val ${placeholder(0)} = 11
          """.trimMargin()
    )
    ScrollableColumn {
      KotlinCode(code, DefaultCodeStyle)
    }
  }
}

val multilineColorSpans =
    listOf(
        SpanStyleRange(normalStyle, 0, 64),
        SpanStyleRange(keywordStyle, 0, 3),
        SpanStyleRange(functionStyle, 4, 11),
        SpanStyleRange(keywordStyle, 14, 17),
        SpanStyleRange(numberStyle, 43, 50),
        SpanStyleRange(keywordStyle, 53, 56),
        SpanStyleRange(numberStyle, 61, 62),
        SpanStyleRange(functionStyle, 63, 64),
    )

@Preview
@Composable
fun PreviewKotlinCode_Multiline() {
  MaterialTheme {
    val code = AnnotatedString(
        """
            |fun foo() {
            |  val bar = ${placeholder(10)} - 1234567
            |  val i = 0
            |}
          """.trimMargin(),
        multilineColorSpans
    )
    ScrollableColumn {
      KotlinCode(code, DefaultCodeStyle)
    }
  }
}

//@Preview
@Composable
fun PreviewKotlinCode_WithLineNumbers() {
  MaterialTheme {
    val code = AnnotatedString(
        """
          |fun foo() {
          |  val bar = /**ANSWER(XX)**/ - 1234567
          |  val i = 0
          |}
        """.trimMargin(),
        multilineColorSpans,
    )
    ScrollableColumn {
      KotlinCode(
          code = code,
          codeStyle = DefaultCodeStyle,
          showLineNumbers = true
      )
    }
  }
}

@Preview
@Composable
fun PreviewKotlinCode_WithLineNumbersAndFocus() {
  MaterialTheme {
    val code = """
          |fun foo() {
          |  val bar = /**ANSWER(XX)**/ - 1234567
          |  val i = 0
          |}
        """.trimMargin()
    val annotated = AnnotatedString(
        code,
        multilineColorSpans,
    )
    val focus = "al i = 0"
    val focusStart = code.indexOf(focus)
    val focusEnd = focusStart + focus.length
    ScrollableColumn {
      KotlinCode(
          code = listOf(
              2 .. 4,
              8 .. 10,
              45..47,
              focusStart..focusEnd
          ).fold(annotated){acc, e ->
            acc.invertForegroundBackgroundColors(e)
          },
          codeStyle = DefaultCodeStyle,
          showLineNumbers = true,
      )
    }
  }
}

//@Preview
@Composable
fun KotlinCodePreview_back2back() {
  MaterialTheme {
    val code = """
        |fun main() {
        |  ${placeholder(0)}(${placeholder(1)}${placeholder(2)}${placeholder(1)})
        |}
      """.trimMargin()
    ScrollableColumn {
      KotlinCode(code = code)
    }
  }
}

@Preview
@Composable
fun KotlinCodePreview_highlight1() {
  MaterialTheme {
    val code = """
        |${placeholder(3)} main() {
        |  ${placeholder(0)}(${placeholder(1)}${placeholder(2)}${placeholder(1)})
        |}
      """.trimMargin()
    ScrollableColumn {
      KotlinCode(code = code, activeHighlight = 1)
    }
  }
}

//@Preview
@Composable
fun KotlinCodePreview_highlight3() {
  MaterialTheme {
    val code = """
        |${placeholder(3)} main() {
        |  ${placeholder(0)}(${placeholder(1)}${placeholder(2)}${placeholder(1)})
        |}
      """.trimMargin()
    ScrollableColumn {
      KotlinCode(code = code, activeHighlight = 3)
    }
  }
}

//@Preview
@Composable
fun PreviewKotlinCode() {
  MaterialTheme {
    val tripleQuote = "\"\"\""
    val dollar = "$"
    val code =
        """
          |package org.kotlinlang.play
          |
          |import com.example.Class
          |import com.example.Class; // semi
          |
          |val string = "Hello \"WORLD\""
          |val tpl = "${'$'}{string + 1}"
          |val multiline = $tripleQuote
          |  Show $dollar{string + 1}"
          |  $dollar{ if(true) 1 else 0 }
          |$tripleQuote
          |
          |fun main() {
          |  ${placeholder(20)}
          |  println("Hello, \"World!\"")
          |}
          |
          |// classes
          |
          |interface Interface<I> where I:Interface<I> {
          |  val i: I
          |  ${placeholder(0)}
          |}
          |
          |class C(override val i:I): 
          |    Interface<I> where I: Interface<I> {
          |  fun foo() {
          |    println("Hello ${'$'}i")
          |    println("${placeholder(1)}")
          |  }
          |}
          |
          |${placeholder(2)}
          |
          |@Annotated
          |data class Foo(val a:String, var b:Int)
          |
          |//34567890123456789012345678901234567890
          |//${placeholder(3)}
          |
          |fun fizzBuzz(
          |  range: IntRange,
          |  config: Map<Int, String> =
          |      /*
          |       * The default for non 
          |       * generic fizzbuzz
          |       */
          |      mapOf(3 to "Fizz", 5 to "Buzz")
          |): List<String> {
          |  return range.map { i ->
          |    var out = ""
          |
          |    config.keys.mapNotNull { k ->
          |      if(i % k == 0) {
          |        out += config[k]
          |      } else ${placeholder(10)}
          |    }.joinToString("")
          |
          |    if(out == "") out = i.toString()
          |
          |    out
          |  }
          |}
        """.trimMargin()

    ScrollableColumn {
      KotlinCode(
          code = extractHighlightsAndAnnotate(code, ijStyle),
          DefaultCodeStyle
      )
    }
  }
}
