package  net.lab0.kotlinexplorer.framework.presentation.composable.frame.drawer

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.navigate
import androidx.navigation.compose.rememberNavController
import net.lab0.kotlinexplorer.framework.presentation.composable.frame.TopLevelScreen

@Composable
fun ExploreDrawer(
  topLevelNavController: NavHostController
) {
  val scrollState = rememberScrollState()

  Column(
    modifier = Modifier
      .verticalScroll(scrollState)
      .padding(horizontal = 32.dp, vertical = 16.dp),
  ) {
    listOf(
      TopLevelScreen.Profile,
      TopLevelScreen.Chapters,
      TopLevelScreen.Tools
    ).forEach {
      DrawerMenuEntry(
        title = it.name,
        icon = it.icon,
        modifier = Modifier.padding(vertical = 16.dp),
        onClick = {
          topLevelNavController.navigate(it.routeDefinition)
        }
      )
    }
  }
}

@Preview
@Composable
fun PreviewExploreDrawer() {
  MaterialTheme {
    Surface {
      Column {
        ExploreDrawer(rememberNavController())
      }
    }
  }
}
