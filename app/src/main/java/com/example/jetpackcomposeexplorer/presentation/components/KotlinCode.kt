package com.example.jetpackcomposeexplorer.presentation.components

import androidx.compose.foundation.ScrollableColumn
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyleRange
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.subSequence
import androidx.compose.ui.tooling.preview.Preview
import com.example.jetpackcomposeexplorer.model.code.CodeStyle
import com.example.jetpackcomposeexplorer.model.code.DefaultCodeStyle
import com.example.jetpackcomposeexplorer.model.code.extractHighlightsAndAnnotate
import com.example.jetpackcomposeexplorer.model.code.functionStyle
import com.example.jetpackcomposeexplorer.model.code.ijStyle
import com.example.jetpackcomposeexplorer.model.code.keywordStyle
import com.example.jetpackcomposeexplorer.model.code.numberStyle
import com.example.jetpackcomposeexplorer.model.KotlinCodeWithBlanks.Companion.ANSWER_REGEX
import com.example.jetpackcomposeexplorer.model.KotlinCodeWithBlanks.Companion.placeholder
import com.example.jetpackcomposeexplorer.ui.theme.length
import net.lab0.grammar.kotlin.KotlinHighlight

@Composable
fun Monospace(text: AnnotatedString) {
  Text(
      text,
      fontFamily = FontFamily.Monospace,
      softWrap = false
  )
}

@Composable
fun KotlinCode(
    code: String,
    codeStyle: CodeStyle<KotlinHighlight> = DefaultCodeStyle
) {
  KotlinCode(
      AnnotatedString(code),
      codeStyle.foregroundColor,
      codeStyle.backgroundColor,
  )
}

@Composable
fun KotlinCode(
    code: AnnotatedString,
    foregroundColor: Color,
    backgroundColor: Color
) {
  val lines = code.text.split("\n")

  Surface(
      modifier = Modifier.fillMaxWidth(),
      color = backgroundColor,
      contentColor = foregroundColor
  ) {
    Column {
      lines.forEachIndexed { index, line ->
        val realStartIndex =
            lines.take(index).fold(0) { acc, e -> acc + e.length + 1 }

        Row {
          val answerMatch = ANSWER_REGEX.find(line)

          if (answerMatch != null) {
            val matchGroup = answerMatch.groups[0]
            val answerIndex = matchGroup!!.range.first

            val placeholderLength = matchGroup.range.length

            val before = line.substring(0, answerIndex)
            val after = line.substring(answerIndex + placeholderLength)

            val realStartOfBefore = realStartIndex
            val realEndOfBefore = realStartIndex + before.length
            val realStartOfAfter = realEndOfBefore + placeholderLength
            val realEndOfAfter = realStartOfAfter + after.length

            Monospace(code.subSequence(realStartOfBefore, realEndOfBefore))
            Surface(
                shape = MaterialTheme.shapes.medium,
                color = MaterialTheme.colors.primaryVariant,
            ) {
              // placeholder to have the right size
              Text(
                  " ".repeat(placeholderLength),
                  fontFamily = FontFamily.Monospace,
                  softWrap = false,
              )
            }
            Monospace(code.subSequence(realStartOfAfter, realEndOfAfter))
          } else {
            Monospace(
                code.subSequence(
                    realStartIndex,
                    realStartIndex + line.length
                )
            )
          }
        }
      }
    }
  }
}

val previewBackgroundColor = Color(0xff2B2B2B)
val previewForegroundColor = Color(0xffA9B7C6)

@Preview
@Composable
fun PreviewKotlinCode_Empty() {
  MaterialTheme {
    val code = AnnotatedString("")

    ScrollableColumn {
      KotlinCode(code, previewForegroundColor, previewBackgroundColor)
    }
  }
}

@Preview
@Composable
fun PreviewKotlinCode_NoAnswer() {
  MaterialTheme {
    val code = AnnotatedString("val i = 0")
    ScrollableColumn {
      KotlinCode(code, previewForegroundColor, previewBackgroundColor)
    }
  }
}

@Preview
@Composable
fun PreviewKotlinCode_println() {
  MaterialTheme {
    val code = AnnotatedString("println(${placeholder()})")
    ScrollableColumn {
      KotlinCode(code, previewForegroundColor, previewBackgroundColor)
    }
  }
}

@Preview
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
      KotlinCode(code, previewForegroundColor, previewBackgroundColor)
    }
  }
}

@Preview
@Composable
fun PreviewKotlinCode_AnswerOnly() {
  MaterialTheme {
    val code = AnnotatedString(
        """
            |val ${placeholder(0)} = 11
          """.trimMargin()
    )
    ScrollableColumn {
      KotlinCode(code, previewForegroundColor, previewBackgroundColor)
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
      KotlinCode(code, previewForegroundColor, previewBackgroundColor)
    }
  }
}

@Preview
@Composable
fun PreviewKotlinCode_MultilineRef() {
  MaterialTheme {
    val code = AnnotatedString(
        """
            |fun foo() {
            |  val bar = /**ANSWER(XX)**/ - 1234567
            |  val i = 0
            |}
          """.trimMargin(),
        multilineColorSpans
    )
    ScrollableColumn {
      KotlinCode(code, previewForegroundColor, previewBackgroundColor)
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
            |      /* The default for non generic fizzbuzz */
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
          previewForegroundColor,
          previewBackgroundColor
      )
    }
  }
}
