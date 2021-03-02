package net.lab0.kotlinexplorer.framework.ui.theme

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun Samples(themeName: String) {
  val pad = Modifier.padding(5.dp)
  Column {
    Text(themeName, modifier = pad)

    Button(onClick = {}, modifier = pad) {
      Text("Default button")
    }

    Button(
        onClick = {},
        modifier = pad,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = MaterialTheme.colors.primaryVariant
        )
    ) {
      Text("Primary variant")
    }

    Button(
        onClick = {},
        modifier = pad,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = MaterialTheme.colors.secondary
        )
    ) {
      Text("Secondary")
    }

    Button(
        onClick = {},
        modifier = pad,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = MaterialTheme.colors.secondaryVariant
        )
    ) {
      Text("Secondary variant")
    }

    Card(modifier = pad) {
      Text(text = "Lorem ipsum sit dolor amet...")
    }
  }
}

@Preview
@Composable
fun ThemePreview_KotlinLight() {
  Column {
    val theme = Theme.KOTLIN_LIGHT
    JetpackComposeExplorerTheme(theme = theme) {
      Surface {
        Samples(theme.toString())
      }
    }
  }
}


@Preview
@Composable
fun ThemePreview() {
  Column {
    val theme = Theme.KOTLIN_DARK
    JetpackComposeExplorerTheme(theme = theme) {
      Surface {
        Samples(theme.toString())
      }
    }
  }
}
