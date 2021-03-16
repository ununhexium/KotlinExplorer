package net.lab0.kotlinexplorer.framework.presentation.composable.frame.drawer

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Place
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun DrawerMenuEntryH5(
  title: String,
  modifier: Modifier = Modifier,
  onClick: (() -> Unit)? = null,
  icon: ImageVector? = null,
) {
  Row(
    modifier = modifier
      .clickable { onClick?.invoke() }
      .padding(vertical = 16.dp)
  ) {
    if (icon != null) {
      Icon(
        icon,
        contentDescription = title,
        modifier = Modifier
          .align(Alignment.CenterVertically)
          .padding(end = 8.dp),
        tint = MaterialTheme.colors.onBackground,
      )
    } else {
      Spacer(modifier = Modifier.width((8 + 16 + 8).dp))
    }

    Text(
      text = title,
      modifier = Modifier.align(Alignment.CenterVertically),
      style = MaterialTheme.typography.h5,
      color = MaterialTheme.colors.onBackground,
    )
  }
}

@Preview
@Composable
private fun DrawerMenuEntryPreview() {
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
          Column {

            DrawerMenuEntryH5(
              title = "Entry title",
              icon = Icons.Default.Place,
              onClick = {}
            )

            DrawerMenuEntryH5(
              title = "Entry without icon",
              icon = null,
              onClick = {}
            )
          }
        }
      }
    }
  }
}