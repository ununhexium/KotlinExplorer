package com.example.jetpackcomposeexplorer.ui.frame

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun CodeButton(text: String, onClick: () -> Unit) {
  Row(
      Modifier
          .clickable(onClick = onClick)
          .border(
              BorderStroke(2.dp, MaterialTheme.colors.secondary),
              RoundedCornerShape(20)
          )
          .padding(4.dp)
  ) {
    Text(text, fontFamily = FontFamily.Monospace)
  }
}

@Preview
@Composable
fun PreviewCodeButton() {
  MaterialTheme {
    Surface {
      CodeButton("Answer") {}
    }
  }
}
