package net.lab0.kotlinexplorer.framework.ui.theme

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun Samples(themeName: String) {
  val pad = Modifier.padding(5.dp)
  Column {
    Text(themeName, modifier = pad)
    Button(onClick = {}, modifier = pad) {
      Text("Button")
    }
    Card(modifier = pad) {
      Text(text = "Card")
    }
  }
}

@Preview
@Composable
fun ThemePreview() {
  Column {
    Theme.values().forEach {
      JetpackComposeExplorerTheme(theme = it) {
        Surface {
          Samples(it.name)
        }
      }
    }
  }
}
