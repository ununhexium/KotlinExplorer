package net.lab0.kotlinexplorer.framework.ui.frame

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import net.lab0.kotlinexplorer.framework.ui.theme.Theme

@Composable
fun ThemeSelection(modifier: Modifier = Modifier) {
  val (theme, setTheme) = remember { mutableStateOf(Theme.LIGHT_DEFAULT) }


}

@Preview
@Composable
fun ThemeSelectionPreview() {
  MaterialTheme {
    Surface {
      ThemeSelection()
    }
  }
}
