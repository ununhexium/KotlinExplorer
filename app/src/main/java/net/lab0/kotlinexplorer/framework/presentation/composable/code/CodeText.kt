package net.lab0.kotlinexplorer.framework.presentation.composable.code

import androidx.compose.foundation.layout.Column
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun CodeText(code: String) {
  Monospace(code)
}

@Preview
@Composable
fun CodeTextPreview() {
  MaterialTheme {
    Surface {
      Column {
        CodeText("Wii")
      }
    }
  }
}
