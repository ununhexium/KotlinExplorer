package net.lab0.kotlinexplorer.framework.presentation.composable.markdown

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.commonmark.node.Document
import org.commonmark.node.Heading

@Composable
fun MDHeading(heading: Heading, modifier: Modifier = Modifier) {
  val style = when (heading.level) {
    1 -> MaterialTheme.typography.h1
    2 -> MaterialTheme.typography.h2
    3 -> MaterialTheme.typography.h3
    4 -> MaterialTheme.typography.h4
    5 -> MaterialTheme.typography.h5
    6 -> MaterialTheme.typography.h6
    else -> {
      // Invalid header...
      MDBlockChildren(heading)
      return
    }
  }

  val padding = if (heading.parent is Document) 8.dp else 0.dp
  Box(modifier = modifier.padding(bottom = padding)) {
    val text = buildAnnotatedString {
      appendMarkdownChildren(heading, MaterialTheme.colors)
    }
    MarkdownText(text, style)
  }
}


@Preview
@Composable
fun MDHeadingPreview() {
  MaterialTheme {
    Surface {
      Column {
        MDDocument(
            document = parseMD(
                """
                  |# Title
                  |## Subtitle
                  |### H3
                  |#### H4
                  |##### H5
                  |###### H6
                """.trimMargin()
            )
        )
        MDDocument(
            document = parseMD(
                """
                  |# Kotlin
                  |
                  |Kotlin is a programming language widely used for Android development 
                  |but also for server-side and tooling development.
                """.trimMargin()
            )
        )
      }
    }
  }
}