package com.example.jetpackcomposeexplorer.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jetpackcomposeexplorer.ui.frame.BigVerticalSpacer

@Composable
fun CorrectAnswer(
    explanation: String,
) {
  val okColor = Color(0xFF4CAF50)
  Column(modifier = Modifier.fillMaxWidth()) {
    Row {
      Icon(
          modifier = Modifier.padding(4.dp),
          imageVector = Icons.Default.CheckCircleOutline,
          tint = okColor,
      )
      Text(
          "Correct",
          modifier = Modifier
              .padding(4.dp)
              .align(Alignment.CenterVertically),
          color = okColor,
      )
    }
    Text(
        explanation,
        modifier = Modifier.padding(4.dp),
    )
  }
}

@Composable
fun WrongAnswer(
    explanation: String,
) {
  val errorColor = Color(0xFFFF5722)

  Column(modifier = Modifier.fillMaxWidth()) {
    Row {
      Icon(
          modifier = Modifier
              .padding(4.dp)
              .align(Alignment.CenterVertically),
          imageVector = Icons.Default.Warning,
          tint = errorColor,
      )
      Text(
          "Incorrect",
          modifier = Modifier
              .padding(4.dp)
              .align(Alignment.CenterVertically),
          color = errorColor
      )
    }
    Text(
        explanation,
        modifier = Modifier.padding(4.dp),
    )
  }
}

@Preview
@Composable
fun AnswerPreview() {
  MaterialTheme {
    Surface {
      Column {
        CorrectAnswer(
            explanation = "This is some explanation about the answer." +
                " It may be very long, so it's better be wrapped." +
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit," +
                " sed do eiusmod tempor incididunt ut labore et dolore magna aliqua." +
                " Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris " +
                "nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in" +
                " reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur." +
                " Excepteur sint occaecat cupidatat non proident, " +
                "sunt in culpa qui officia deserunt mollit anim id est laborum."
        )
        BigVerticalSpacer()
        WrongAnswer(
            explanation = "This is some explanation about the answer." +
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
    }
  }
}