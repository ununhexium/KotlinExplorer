package com.example.jetpackcomposeexplorer.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun Quiz(
    progress: Float,
    page: @Composable () -> Unit,
) {
  Column(modifier = Modifier.fillMaxSize()) {
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
        Quiz(
            0.116f,
        ) {
          Surface(modifier = Modifier.fillMaxSize(), color = Color.Gray) {

          }
        }
      }
    }
  }
}
