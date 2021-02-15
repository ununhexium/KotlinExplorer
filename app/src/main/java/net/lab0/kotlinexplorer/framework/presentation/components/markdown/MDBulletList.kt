package net.lab0.kotlinexplorer.framework.presentation.components.markdown

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
import org.commonmark.node.BulletList


@Composable
fun MDBulletList(bulletList: BulletList, modifier: Modifier = Modifier) {
  val marker = "\u2022" // middle point '•'
  MDListItems(bulletList, modifier = modifier) {
    val text = buildAnnotatedString {
      pushStyle(MaterialTheme.typography.body1.toSpanStyle())
      append("$marker ")
      appendMarkdownChildren(it, MaterialTheme.colors)
      pop()
    }
    MarkdownText(text, MaterialTheme.typography.body1, modifier)
  }
}

@Preview
@Composable
fun MDBulletListPreview() {
  MaterialTheme {
    Surface(
        color = Color(0xFF4CAF50)
    ) {
      Surface(
          modifier = Modifier.padding(20.dp),
          color = MaterialTheme.colors.surface,
      ) {
        Column {
          MDDocument(
              document = parseMD(
                  """
                    |The list:
                    |* one
                    |* 2
                    |* trois
                  """.trimMargin()
              )
          )
        }
      }
    }
  }
}
