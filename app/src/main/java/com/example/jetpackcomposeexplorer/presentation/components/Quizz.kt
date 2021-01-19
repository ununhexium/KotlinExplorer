package com.example.jetpackcomposeexplorer.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun LessonPage(
    progress: Float,
    title: String,
    page: @Composable () -> Unit,
) {
  Column(modifier = Modifier.fillMaxSize()) {
    Text(
        title,
        modifier = Modifier
            .padding(8.dp)
            .align(Alignment.CenterHorizontally),
        style = MaterialTheme.typography.body2
    )
    page()
    LinearProgressIndicator(progress = progress, modifier = Modifier.fillMaxWidth())
  }
}

@Preview
@Composable
fun QuizPreview() {
  MaterialTheme {
    Surface {
      Column {
        LessonPage(
            0.116f,
            "Somewhere over the rainbow"
        ) {
          Surface(modifier = Modifier.fillMaxSize(), color = Color.Gray) {

          }
        }
      }
    }
  }
}
