package com.example.jetpackcomposeexplorer.ui.frame.preview

import androidx.compose.foundation.layout.Row
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun PreviewTopBar(modifier: Modifier = Modifier) {
  Row {
    Icon(Icons.Default.Menu)
    Text("Lorem Ipsum")
  }
}

@Preview
@Composable
fun PreviewPreviewTopBar() {
  MaterialTheme {
    Surface {
      PreviewTopBar()
    }
  }
}
