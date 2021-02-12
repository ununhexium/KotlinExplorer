package com.example.jetpackcomposeexplorer.framework.presentation.components.code

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
import com.example.jetpackcomposeexplorer.business.domain.KotlinCodeWithBlanks.CodeType.CODE
import com.example.jetpackcomposeexplorer.business.domain.KotlinCodeWithBlanks.CodeType.PLACEHOLDER
import com.example.jetpackcomposeexplorer.business.domain.KotlinCodeWithBlanks.Companion.placeholder
import com.example.jetpackcomposeexplorer.business.domain.KotlinCodeWithBlanksImpl
import com.example.jetpackcomposeexplorer.framework.ui.theme.sourceCodeFontFamily
import com.example.jetpackcomposeexplorer.model.code.DefaultCodeStyle
import com.example.jetpackcomposeexplorer.model.code.extractHighlightsAndAnnotate
import com.example.jetpackcomposeexplorer.model.code.functionStyle
import com.example.jetpackcomposeexplorer.model.code.ijStyle
import com.example.jetpackcomposeexplorer.model.code.keywordStyle
import com.example.jetpackcomposeexplorer.model.code.numberStyle
import net.lab0.grammar.kotlin.KotlinHighlight


@Composable
fun KotlinCode(
    code: String,
    codeStyle: CodeStyle<KotlinHighlight> = DefaultCodeStyle,
    showLineNumbers: Boolean = false,
) {
  KotlinCode(
      AnnotatedString(code),
      codeStyle,
      showLineNumbers
  )
}

@Composable
fun KotlinCode(
    code: AnnotatedString,
    codeStyle: CodeStyle<KotlinHighlight> = DefaultCodeStyle,
    showLineNumbers: Boolean = false,
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
          codeStyle = codeStyle
      )
    }
  }
}

@Composable
fun GutterPart(
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
) {
  Column {
    lines.forEachIndexed { index, line ->
      val realStartIndex =
          lines.take(index).fold(0) { acc, e -> acc + e.length + 1 }

      Row {
        KotlinCodeWithBlanksImpl(line).split().forEach {
          when (it.first) {
            PLACEHOLDER ->
              Surface(
                  modifier = Modifier.padding(horizontal = 2.dp).align(Alignment.CenterVertically),
                  shape = MaterialTheme.shapes.small,
                  color = codeStyle.placeholderBackgroundColor,
                  contentColor = codeStyle.placeholderForegroundColor,
              ) {
                // placeholder to have the right size
                Text(
                    text = "...",
                    fontFamily = sourceCodeFontFamily,
                    fontSize = TextUnit.Companion.Sp(10)
                )
              }
            CODE ->
              Monospace(
                  code.subSequence(
                      realStartIndex + it.second.first,
                      realStartIndex + it.second.last + 1
                  )
              )
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

@Preview
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

//@Preview
@Composable
fun PreviewKotlinCode() {
  MaterialTheme {
    val code =
        """
          |package org.kotlinlang.play
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
          |fun main() {
          |  ${placeholder(20)}
          |  println("Hello, World!")
          |}
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
