package net.lab0.kotlinexplorer.framework.presentation.composable.frame

import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import net.lab0.kotlinexplorer.framework.presentation.composable.frame.drawer.KDrawer
import net.lab0.kotlinexplorer.framework.presentation.composable.frame.topbar.KTopAppBar
import net.lab0.kotlinexplorer.framework.presentation.composable.visualizer.bool.BooleanOperator
import net.lab0.kotlinexplorer.framework.presentation.composable.visualizer.bool.BooleanVisualizer


@Composable
fun BooleanVisualizerUi(
  navHostController: NavHostController,
  initialValue1: Boolean,
  initialValue2: Boolean,
  initialOperator: BooleanOperator
) {
  val scaffoldState = rememberScaffoldState()

  Scaffold(
    scaffoldState = scaffoldState,

    //top bar
    topBar = {
      KTopAppBar(scaffoldState = scaffoldState, title = "Boolean")
    },

    // drawer
    drawerContent = {
      KDrawer {
      }
    }
  ) {
    BooleanVisualizer(
      initialValue1 = initialValue1,
      initialValue2 = initialValue2,
      initialOperator = initialOperator,
    )
  }
}
