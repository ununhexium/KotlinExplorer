package net.lab0.kotlinexplorer.framework.presentation.composable.frame.text

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Composable
fun H5Text(string: String) {
  Text(string, style = MaterialTheme.typography.h5)
}

@Composable
fun H6Text(string: String) {
  Text(string, style = MaterialTheme.typography.h6)
}

@Composable
fun Body1Text(string: String) {
  Text(string, style = MaterialTheme.typography.body1)
}

@Composable
fun Body2Text(string: String) {
  Text(string, style = MaterialTheme.typography.body2)
}

@Preview
@Composable
private fun Previews() {
  MaterialTheme {
    Surface(
      color = Color(0xFF4CAF50)
    ) {
      Column(
        modifier = Modifier.padding(20.dp)
      ) {
        Surface(
          color = MaterialTheme.colors.background
        ) {
          Body1Text("Body1")
        }
      }
    }
  }
}