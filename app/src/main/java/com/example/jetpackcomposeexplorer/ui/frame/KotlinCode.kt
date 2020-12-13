package com.example.jetpackcomposeexplorer.ui.frame

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ScrollableColumn
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.unit.dp
import androidx.ui.tooling.preview.Preview
import com.example.jetpackcomposeexplorer.code.extractHighlightsAndAnnotate
import com.example.jetpackcomposeexplorer.code.functionStyle
import com.example.jetpackcomposeexplorer.code.ijStyle
import com.example.jetpackcomposeexplorer.code.keywordStyle
import com.example.jetpackcomposeexplorer.code.numberStyle
import com.example.jetpackcomposeexplorer.ui.length


fun placeholder(index: Int) = "/**ANSWER($index)**/"

val ANSWER_REGEX = Regex("""/\*\*ANSWER\([0-9]{1,2}\)\*\*/""")

@Composable
fun Monospace(text: AnnotatedString) {
  Text(
      text,
      fontFamily = FontFamily.Monospace,
      softWrap = false
  )
}

@Composable
fun Monospace(text: String) {
  Text(
      text,
      fontFamily = FontFamily.Monospace,
      softWrap = false
  )
}

@Composable
fun KotlinCode(code: AnnotatedString) {
  ScrollableColumn {
    val lines = code.text.split("\n")

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
          Box(
              Modifier.border(
                  BorderStroke(2.dp, MaterialTheme.colors.primary),
                  RoundedCornerShape(20)
              )
          ) {
            // placeholder to have the right size
            Monospace(" ".repeat(placeholderLength))
          }
          Monospace(code.subSequence(realStartOfAfter, realEndOfAfter))
        } else {
          Monospace(code.subSequence(realStartIndex, realStartIndex + line.length))
        }
      }
    }
  }
}

@Preview
@Composable
fun PreviewKotlinCode_Empty() {
  MaterialTheme {
    Surface(
        color = Color(0xff2B2B2B),
        contentColor = Color(0xffA9B7C6)
    ) {
      val code = AnnotatedString("")
      KotlinCode(code = code)
    }
  }
}

@Preview
@Composable
fun PreviewKotlinCode_NoAnswer() {
  MaterialTheme {
    Surface(
        color = Color(0xff2B2B2B),
        contentColor = Color(0xffA9B7C6)
    ) {
      val code = AnnotatedString("val i = 0")
      KotlinCode(code = code)
    }
  }
}

@Preview
@Composable
fun PreviewKotlinCode_NewLine() {
  MaterialTheme {
    Surface(
        color = Color(0xff2B2B2B),
        contentColor = Color(0xffA9B7C6)
    ) {
      val code = AnnotatedString(
          """
            |val i = 0
            |fun foo(){
            |  
            |}
          """.trimMargin()
      )
      KotlinCode(code = code)
    }
  }
}

@Preview
@Composable
fun PreviewKotlinCode_AnswerOnly() {
  MaterialTheme {
    Surface(
        color = Color(0xff2B2B2B),
        contentColor = Color(0xffA9B7C6)
    ) {
      val code = AnnotatedString(
          """
            |val ${placeholder(0)} = 11
          """.trimMargin()
      )
      KotlinCode(code = code)
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
    Surface(
        color = Color(0xff2B2B2B),
        contentColor = Color(0xffA9B7C6)
    ) {
      val code = AnnotatedString(
          """
            |fun foo() {
            |  val bar = ${placeholder(10)} - 1234567
            |  val i = 0
            |}
          """.trimMargin(),
          multilineColorSpans
      )
      KotlinCode(code = code)
    }
  }
}

@Preview
@Composable
fun PreviewKotlinCode_MultilineRef() {
  MaterialTheme {
    Surface(
        color = Color(0xff2B2B2B),
        contentColor = Color(0xffA9B7C6)
    ) {
      val code = AnnotatedString(
          """
            |fun foo() {
            |  val bar = /**ANSWER(XX)**/ - 1234567
            |  val i = 0
            |}
          """.trimMargin(),
          multilineColorSpans
      )
      KotlinCode(code = code)
    }
  }
}

@Preview
@Composable
fun PreviewKotlinCode() {
  MaterialTheme {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color(0xff2B2B2B),
        contentColor = Color(0xffA9B7C6)
    ) {
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

      KotlinCode(code = extractHighlightsAndAnnotate(code, ijStyle))
    }
  }
}