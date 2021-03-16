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
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import net.lab0.kotlinexplorer.framework.presentation.composable.frame.Indent

@Composable
fun DrawerMenuEntryBody1(
  title: String,
  modifier: Modifier = Modifier,
  onClick: (() -> Unit)? = null,
  icon: ImageVector? = null,
  fontFamily: FontFamily? = null,
) {
  Row(
    modifier = modifier
      .clickable { onClick?.invoke() }
      .padding(vertical = 4.dp)
  ) {
    val color =
      if (onClick == null) Color.Gray
      else MaterialTheme.colors.secondary

    if (icon != null) {
      Icon(
        icon,
        contentDescription = title,
        modifier = Modifier
          .align(Alignment.CenterVertically)
          .padding(end = 8.dp),
        tint = color,
      )
    } else {
      Spacer(modifier = Modifier.width((8 + 16 + 8).dp))
    }

    Indent(1) {
      Text(
        text = title,
        modifier = Modifier.align(Alignment.CenterVertically),
        style = MaterialTheme.typography.body1,
        color = color,
        fontFamily = fontFamily,
      )
    }
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

            DrawerMenuEntryH5(title = "Entry H5")

            DrawerMenuEntryH6(
              title = "Entry title",
              icon = Icons.Default.Place,
              onClick = {}
            )

            DrawerMenuEntryBody1(title = "Body 1A")
            DrawerMenuEntryBody1(title = "Body 1B")
            DrawerMenuEntryBody1(title = "Body 1C")
            DrawerMenuEntryBody1(title = "Body 1D")

            DrawerMenuEntryH6(
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
