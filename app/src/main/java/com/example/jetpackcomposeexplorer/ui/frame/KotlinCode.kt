package com.example.jetpackcomposeexplorer.ui.frame

import androidx.compose.foundation.ScrollableColumn
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.ui.tooling.preview.Preview
import com.example.jetpackcomposeexplorer.code.extractHighlightsAndAnnotate
import com.example.jetpackcomposeexplorer.code.ijStyle
import net.lab0.grammar.kotlin.Highlights
import net.lab0.grammar.kotlin.KotlinHighlight

@Composable
fun KotlinCode(code: AnnotatedString) {
  ScrollableColumn {
    val parts = code.text.split(placeholder)
    
    Text(
        code,
        fontFamily = FontFamily.Monospace
    )
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
            |  $placeholder
            |}
            |
            |class C(override val i:I): 
            |    Interface<I> where I: Interface<I> {
            |  fun foo() {
            |    println("Hello ${'$'}i")
            |    println("$placeholder")
            |  }
            |}
            |
            |$placeholder
            |
            |fun main() {
            |  println("Hello, World!")
            |}
            |
            |@Annotated
            |data class Foo(val a:String, var b:Int)
            |
            |//34567890123456789012345678901234567890
            |//$placeholder
            |
            |fun fizzBuzz(
            |  range: IntRange,
            |  config: Map<Int, String> =
            |      mapOf(3 to "Fizz", 5 to "Buzz")
            |): List<String> {
            |  return range.map { i ->
            |    var out = ""
            |
            |    $placeholder
            |    config.keys.mapNotNull { k ->
            |      if(i % k == 0) {
            |        out += config[k]
            |      } else null
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
