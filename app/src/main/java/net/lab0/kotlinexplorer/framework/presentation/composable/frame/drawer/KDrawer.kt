package  net.lab0.kotlinexplorer.framework.presentation.composable.frame.drawer

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ZoomIn
import androidx.compose.material.icons.filled.ZoomOut
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.navigate
import androidx.navigation.compose.rememberNavController
import net.lab0.kotlinexplorer.framework.presentation.composable.MediumVerticalSpacer
import net.lab0.kotlinexplorer.framework.presentation.composable.frame.TopLevelScreen

@Composable
fun KDrawer(
  entries: @Composable ColumnScope.() -> Unit,
) {
  val scrollState = rememberScrollState()

  Column(
    modifier = Modifier
      .verticalScroll(scrollState)
      .padding(horizontal = 32.dp, vertical = 16.dp),
  ) {
    entries()
  }

  MediumVerticalSpacer()
}

@Preview
@Composable
fun PreviewKDrawer() {
  MaterialTheme {
    Surface {
      KDrawer {
        DrawerMenuEntryH5(title = "Example")
        DrawerMenuEntryH5(title = "Example2", icon = Icons.Default.ZoomIn)
        DrawerMenuEntryH5(title = "Example3", icon = Icons.Default.ZoomOut)
      }
    }
  }
}
