package com.example.jetpackcomposeexplorer.ui.frame

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.ui.tooling.preview.Preview
import com.example.jetpackcomposeexplorer.code.Highlights
import com.example.jetpackcomposeexplorer.code.KotlinHighlight
import com.example.jetpackcomposeexplorer.code.extractHighlightsAndAnnotate
import com.example.jetpackcomposeexplorer.code.functionDeclarationStyle
import com.example.jetpackcomposeexplorer.code.ijStyle
import com.example.jetpackcomposeexplorer.code.keywordStyle
import com.example.jetpackcomposeexplorer.code.stringStyle

@Composable
fun KotlinCode(code: String, highlights: Highlights<KotlinHighlight>) {

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
            |fun main() {
            |    println("Hello, World!")
            |}
            |
            |@Annotated
            |data class Foo(val a:String, var b:Int)
          """.trimMargin()

      Text(
          extractHighlightsAndAnnotate(code, ijStyle),
          fontFamily = FontFamily.Monospace
      )
    }
  }
}
