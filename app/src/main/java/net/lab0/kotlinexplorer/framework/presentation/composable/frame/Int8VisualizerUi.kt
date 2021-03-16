package net.lab0.kotlinexplorer.framework.presentation.composable.frame

import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.text.font.FontFamily
import androidx.navigation.NavHostController
import androidx.navigation.compose.navigate
import androidx.navigation.compose.popUpTo
import net.lab0.kotlinexplorer.framework.presentation.composable.frame.drawer.DrawerMenuEntryBody1
import net.lab0.kotlinexplorer.framework.presentation.composable.frame.drawer.KDrawer
import net.lab0.kotlinexplorer.framework.presentation.composable.frame.topbar.KTopAppBar
import net.lab0.kotlinexplorer.framework.presentation.composable.math.Int8Visualizer

@Composable
fun Int8VisualizerUi(navHostController: NavHostController, initialByte: Byte) {
  val scaffoldState = rememberScaffoldState()
  val byteState = remember { mutableStateOf(initialByte) }

  Scaffold(
    scaffoldState = scaffoldState,

    //top bar
    topBar = {
      KTopAppBar(scaffoldState = scaffoldState, title = "Byte")
    },

    // drawer
    drawerContent = {
      KDrawer {
        DrawerMenuEntryBody1(
          title = "Type converters"
        )
        DrawerMenuEntryBody1(
          title = ".toFloat()",
          onClick = {
            navHostController.navigate(
              ToolScreens.FloatVisualizer.route(byteState.value.toFloat())
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
    Int8Visualizer(byteState)
  }
}
