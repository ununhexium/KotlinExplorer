package com.example.jetpackcomposeexplorer.ui.markdown

import androidx.compose.foundation.ScrollableColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.compose.ui.tooling.preview.Preview
import com.example.jetpackcomposeexplorer.ui.frame.MDDocument
import org.commonmark.parser.Parser


@Composable
fun MarkdownSample(modifier: Modifier = Modifier) {
  val md =
      """
        |# Title
        |
        |Let's see if this works.
        |
        |## Subtitle
        |
        |And then improve it to support Kotlin code samples.
        |
        |```kotlin
        |val i = 0
        |fun main() {
        |  println("Hello Markdown!")
        |}
        |```
      """.trimMargin()

  val parser = Parser.builder().build()
  val document = parser.parse(md)

  ScrollableColumn {
    MDDocument(document)
  }
}

@Preview
@Composable
fun PreviewMarkdownSample() {
  MaterialTheme {
    ScrollableColumn {
      MarkdownSample()
    }
  }
}
