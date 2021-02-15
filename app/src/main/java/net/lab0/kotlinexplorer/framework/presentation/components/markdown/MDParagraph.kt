package net.lab0.kotlinexplorer.framework.presentation.components.markdown

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.commonmark.node.Document
import org.commonmark.node.Image
import org.commonmark.node.Paragraph

@Composable
fun MDParagraph(paragraph: Paragraph, modifier: Modifier = Modifier) {
  if (paragraph.firstChild is Image && paragraph.firstChild == paragraph.lastChild) {
    // Paragraph with single image
    MDImage(paragraph.firstChild as Image, modifier)
  } else {
    val padding = if (paragraph.parent is Document) 8.dp else 0.dp
    Box(modifier = modifier.padding(bottom = padding)) {
      val styledText = buildAnnotatedString {
        pushStyle(MaterialTheme.typography.body1.toSpanStyle())
        appendMarkdownChildren(paragraph, MaterialTheme.colors)
        pop()
      }
      MarkdownText(styledText, MaterialTheme.typography.body1)
    }
  }
}

@Preview
@Composable
fun MDParagraphPreview_SplitLine() {
  MaterialTheme {
    Surface(
        color = Color(0xFF4CAF50)
    ) {
      Column(
          modifier = Modifier.padding(20.dp)
      ) {
        Surface(
            color = MaterialTheme.colors.surface
        ) {
          MDDocument(
              document = parseMD(
                  """
                    |This
                    |must
                    |be
                    |on
                    |a
                    |single
                    |line
                  """.trimMargin()
              )
          )
        }
      }
    }
  }
}

@Preview
@Composable
fun MDParagraphPreview_Multiline() {
  MaterialTheme {
    Surface(
        color = Color(0xFF4CAF50)
    ) {
      Column(
          modifier = Modifier.padding(20.dp)
      ) {
        Surface(
            color = MaterialTheme.colors.surface
        ) {
          Column {
            MDDocument(
                parseMD(
                    """
`print` to show the value on the terminal.

`"` to quote the string.

`Hello, World!` for the content.
"""
                )
            )
          }
        }
      }
    }
  }
}
