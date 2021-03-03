package net.lab0.kotlinexplorer.framework.presentation.composable.lesson

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircleOutline
import androidx.compose.material.icons.filled.Warning
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import net.lab0.kotlinexplorer.framework.presentation.composable.markdown.MDDocument
import net.lab0.kotlinexplorer.framework.presentation.composable.markdown.parseMD

@Composable
private fun Answer(
    header: String,
    color: Color,
    icon: ImageVector,
    contentDescription: String,
    content: @Composable ColumnScope.() -> Unit,
) {
  Column(modifier = Modifier.fillMaxSize(1f)) {
    Row(
        modifier = Modifier
            .padding(bottom = 8.dp),
    ) {
      Icon(
          imageVector = icon,
          contentDescription = contentDescription,
          modifier = Modifier
              .padding(start = 8.dp, end = 8.dp)
              .align(Alignment.CenterVertically),
          tint = color,
      )
      Text(
          header,
          modifier = Modifier
              .align(Alignment.CenterVertically),
          color = color,
          style = MaterialTheme.typography.h5
      )
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        content = content,
    )
  }
}

@Composable
fun CorrectAnswer(
    explanation: @Composable ColumnScope.() -> Unit,
) {
  Answer(
      "Correct",
      Color(0xFF4CAF50),
      Icons.Default.CheckCircleOutline,
      contentDescription = "outline",
      explanation
  )
}

@Composable
fun WrongAnswer(
    explanation: @Composable ColumnScope.() -> Unit,
) {
  Answer(
      "Incorrect",
      Color(0xFFFF5722),
      Icons.Default.Warning,
      contentDescription = "warning",
      explanation
  )
}

@Preview
@Composable
fun CorrectAnswerPreview() {
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
          CorrectAnswer(
              explanation = {
                Text(
                    "This is some explanation about the answer." +
                        " It may be very long, so it's better be wrapped." +
                        "Lorem ipsum dolor sit amet, consectetur adipiscing elit," +
                        " sed do eiusmod tempor incididunt ut labore et dolore magna aliqua." +
                        " Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris " +
                        "nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in" +
                        " reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur." +
                        " Excepteur sint occaecat cupidatat non proident, " +
                        "sunt in culpa qui officia deserunt mollit anim id est laborum."
                )
              }
          )
        }
      }
    }
  }
}

@Preview
@Composable
fun WrongAnswerPreview() {
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
          WrongAnswer(
              explanation = {
                Text(
                    "This is some explanation about the answer." +
                        " It may be very long, so it's better be wrapped." +
                        "Lorem ipsum dolor sit amet, consectetur adipiscing elit," +
                        " sed do eiusmod tempor incididunt ut labore et dolore magna aliqua." +
                        " Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris " +
                        "nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in" +
                        " reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur." +
                        " Excepteur sint occaecat cupidatat non proident, " +
                        "sunt in culpa qui officia deserunt mollit anim id est laborum."
                )
              }
          )
        }
      }
    }
  }
}

@Preview
@Composable
fun CorrectAnswerPreview_Markdown() {
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
          CorrectAnswer(
              explanation = {
                MDDocument(
                    document = parseMD(
                        """
`print` to show the value on the terminal.

`"` to quote the string.

`Hello, World!` for the content.
"""
                    )
                )
              }
          )
        }
      }
    }
  }
}
