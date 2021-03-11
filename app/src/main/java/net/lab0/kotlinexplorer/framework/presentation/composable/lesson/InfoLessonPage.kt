package net.lab0.kotlinexplorer.framework.presentation.composable.lesson

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import net.lab0.kotlinexplorer.framework.presentation.composable.code.input.ControlBar
import net.lab0.kotlinexplorer.framework.presentation.composable.markdown.MDDocument
import net.lab0.kotlinexplorer.framework.presentation.composable.markdown.parseMD

@Composable
fun InfoLessonPage(
  markdownAsString: String,
  nextPage: () -> Unit,
) {
  LessonPageBody(
    question = {
      val markdown = remember(markdownAsString) { parseMD(markdownAsString) }
      MDDocument(document = markdown)
    },
    controlBar = {
      ControlBar {
        Button(
          onClick = nextPage,
        ) {
          Icon(
            imageVector = Icons.Default.CheckCircle,
            contentDescription = "Check Circle",
          )
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
          """
                  |# Title
                  |
                  |1. One
                  |1. Two
                  |
                  |* true
                  |* false
                """.trimMargin()
        ) {}
      }
    }
  }
}
