package net.lab0.kotlinexplorer.framework.presentation.composable.frame

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.navigate
import net.lab0.kotlinexplorer.framework.presentation.composable.frame.drawer.DrawerMenuEntry
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
      KTopAppBar(scaffoldState = scaffoldState, title = "Integer Visualizer")
    },

    // drawer
    drawerContent = {
      Column {
        DrawerMenuEntry(
          title = ".toFloat()",
          onClick = {
            navHostController.navigate(
              ToolScreens.FloatVisualizer.route(byteState.value.toFloat())
            )
          }
        )
      }
    }
  ) {
    Int8Visualizer(byteState)
  }
}
