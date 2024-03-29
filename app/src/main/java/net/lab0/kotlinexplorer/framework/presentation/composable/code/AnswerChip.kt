package net.lab0.kotlinexplorer.framework.presentation.composable.code

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Composable
fun AnswerChip(
  text: String,
  enabled: Boolean,
  onClick: () -> Unit,
) {
  Surface(
    elevation = 4.dp,
    shape = MaterialTheme.shapes.medium,
  ) {
    Button(
      onClick = onClick,
      enabled = enabled,
      colors = ButtonDefaults.buttonColors(
        backgroundColor = MaterialTheme.colors.secondary
      )
    ) {
      Monospace(text)
    }
  }
}

@Preview
@Composable
fun AnswerChipPreview() {
  MaterialTheme {
    Surface {
      Column {
        AnswerChip("Wii", true) {}
        AnswerChip("Wii", false) {}
      }
    }
  }
}
