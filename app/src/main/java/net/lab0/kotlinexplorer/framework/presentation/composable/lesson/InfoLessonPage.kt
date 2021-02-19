package net.lab0.kotlinexplorer.framework.presentation.composable.lesson

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import net.lab0.kotlinexplorer.framework.presentation.composable.code.input.ControlBar
import net.lab0.kotlinexplorer.framework.presentation.composable.markdown.MDDocument
import org.commonmark.node.Node
import org.commonmark.parser.Parser

@Composable
fun InfoLessonPage(
    node: Node,
    nextPage: () -> Unit,
) {
  LessonPageBody(
      question = {
        MDDocument(document = node)
      },
      controlBar = {
        ControlBar {
          Button(
              onClick = nextPage,
          ) {
            Icon(imageVector = Icons.Default.CheckCircle)
          }
        }
      }
  )
}

@Preview
@Composable
fun InfoLessonPagePreview() {
  MaterialTheme {
    Surface {
      Column {
        InfoLessonPage(
            Parser.builder().build().parse(
                """
                  |# Title
                  |
                  |1. One
                  |1. Two
                  |
                  |* true
                  |* false
                """.trimMargin()
            )
        ) {}
      }
    }
  }
}
