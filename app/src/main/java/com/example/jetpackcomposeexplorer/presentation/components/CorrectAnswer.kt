package com.example.jetpackcomposeexplorer.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
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

@Composable
private fun Answer(
    header: String,
    color: Color,
    icon: ImageVector,
    content: @Composable RowScope.() -> Unit,
) {
  Column(modifier = Modifier.fillMaxWidth()) {
    Row(
        modifier = Modifier
            .padding(bottom = 8.dp),
    ) {
      Icon(
          imageVector = icon,
          modifier = Modifier
              .padding(start = 8.dp,end = 8.dp)
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

    Row(
        content = content,
    )
  }
}

@Composable
fun CorrectAnswer(
    explanation: @Composable RowScope.() -> Unit,
) {
  Answer(
      "Correct",
      Color(0xFF4CAF50),
      Icons.Default.CheckCircleOutline,
      explanation
  )
}

@Composable
fun WrongAnswer(
    explanation: @Composable RowScope.() -> Unit,
) {
  Answer(
      "Incorrect",
      Color(0xFFFF5722),
      Icons.Default.Warning,
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

