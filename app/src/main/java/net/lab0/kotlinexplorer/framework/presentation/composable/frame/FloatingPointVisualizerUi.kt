package net.lab0.kotlinexplorer.framework.presentation.composable.frame

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.navigate
import androidx.navigation.compose.popUpTo
import androidx.navigation.compose.rememberNavController
import net.lab0.kotlinexplorer.framework.presentation.composable.frame.drawer.DrawerMenuEntryBody1
import net.lab0.kotlinexplorer.framework.presentation.composable.frame.drawer.KDrawer
import net.lab0.kotlinexplorer.framework.presentation.composable.frame.topbar.KTopAppBar
import net.lab0.kotlinexplorer.framework.presentation.composable.visualizer.floatingpoint.DatedFloat
import net.lab0.kotlinexplorer.framework.presentation.composable.visualizer.floatingpoint.FloatingPointVisualizer

@Composable
fun FloatingPointVisualizerUi(navHostController: NavHostController, initialFloat: Float) {
  val scaffoldState = rememberScaffoldState()
  val floatState = remember { mutableStateOf(DatedFloat(initialFloat)) }

  Scaffold(
    scaffoldState = scaffoldState,

    //top bar
    topBar = {
      KTopAppBar(scaffoldState = scaffoldState, title = "Float")
    },

    // drawer
    drawerContent = {
      KDrawer {
        DrawerMenuEntryBody1(
          title = "Type converters"
        )
        DrawerMenuEntryBody1(
          title = ".toByte()",
          onClick = {
            navHostController.navigate(
              ToolScreens.IntVisualizer.route(floatState.value.float.toLong())
            ) {
              popUpTo(ToolScreens.List.routeDefinition) {
                inclusive = false
              }
            }
          },
          fontFamily = FontFamily.Monospace,
        )
      }
    }
  ) {
    FloatingPointVisualizer(floatState)
  }
}

@Preview
@Composable
private fun FloatingPointVisualizerUiPreview() {
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
          FloatingPointVisualizerUi(rememberNavController(), -116f)
        }
      }
    }
  }
}
