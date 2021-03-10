package net.lab0.kotlinexplorer.framework.presentation.composable.frame

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckBox
import androidx.compose.material.icons.filled.CheckBoxOutlineBlank
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun Checkable(
  checked: Boolean,
  onCheckedChange: (Boolean) -> Unit,
  modifier: Modifier = Modifier,
  color: Color = MaterialTheme.colors.onBackground,
  content: @Composable () -> Unit,
) {
  Row(
    modifier = modifier.clickable {
      onCheckedChange(!checked)
    },
  ) {
    Icon(
      if (checked) {
        Icons.Default.CheckBox
      } else {
        Icons.Default.CheckBoxOutlineBlank
      },
      contentDescription = "Checkbox",
      tint = color,
    )

    content()
  }
}

@Preview
@Composable
fun CheckableBoxPreview() {
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
          Checkable(true, {}) { Text("Foo") }
        }
      }
    }
  }
}