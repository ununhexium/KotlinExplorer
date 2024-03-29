package net.lab0.kotlinexplorer.framework.presentation.composable.frame

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import net.lab0.kotlinexplorer.framework.presentation.composable.BigVerticalSpacer
import net.lab0.kotlinexplorer.framework.presentation.composable.MediumHorizontalSpacer

@Composable
fun Indent(
  indent: Int = 1,
  content: @Composable () -> Unit,
) {
  Row {
    (1 .. indent).forEach {
      MediumHorizontalSpacer()
    }
    content()
  }
}

@Preview
@Composable
fun IndentPreview() {
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
          Column {
            Indent(1) {
              Text("1 down")
            }
            BigVerticalSpacer()
            Indent(2) {
              Text("2 down")
            }
          }
        }
      }
    }
  }
}