package net.lab0.kotlinexplorer.framework.presentation.composable.frame

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
import net.lab0.kotlinexplorer.framework.presentation.composable.DefaultVerticalSpacer
import java.util.*

@Composable
fun ThinButton(
  text: String,
  modifier: Modifier = Modifier,
  highlight: Boolean = true,
  onClick: () -> Unit,
) {
  Surface(modifier, color = MaterialTheme.colors.surface) {
    val color = if (highlight) {
      MaterialTheme.colors.primary
    } else {
      MaterialTheme.colors.onSurface
    }

    Text(
      text.toUpperCase(Locale.getDefault()),
      modifier = Modifier
        .clickable { onClick() }
        .padding(horizontal = 3.dp),
      style = MaterialTheme.typography.body1,
      color = color,
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
        Column {
          ThinButton(
            text = "Example",
            highlight = true
          ) {}
          DefaultVerticalSpacer()
          ThinButton(
            text = "Example",
            highlight = false
          ) {}
        }
      }
    }
  }
}
