package net.lab0.kotlinexplorer.framework.presentation.composable.frame

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import java.util.*

@Composable
fun ThinButton(
    text: String,
    onClick: () -> Unit,
) {
  Surface(color = MaterialTheme.colors.surface) {
    Text(
        text.toUpperCase(Locale.getDefault()),
        modifier = Modifier
            .border(1.dp, MaterialTheme.colors.primary)
            .clickable { onClick() },
        style = MaterialTheme.typography.body1
    )
  }
}

@Preview
@Composable
fun ThinButtonPreview() {
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
          ThinButton("Example")
        }
      }
    }
  }
}