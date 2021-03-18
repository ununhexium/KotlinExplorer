package net.lab0.kotlinexplorer.framework.presentation.composable.frame

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.navigate
import androidx.navigation.compose.popUpTo
import net.lab0.kotlinexplorer.framework.presentation.composable.frame.drawer.DrawerMenuEntryBody1
import net.lab0.kotlinexplorer.framework.presentation.composable.frame.drawer.KDrawer
import net.lab0.kotlinexplorer.framework.presentation.composable.frame.topbar.KTopAppBar
import net.lab0.kotlinexplorer.framework.presentation.composable.math.Int8Visualizer
import net.lab0.kotlinexplorer.framework.presentation.composable.project.PositiveNegativeProject

@Composable
fun PositiveNegativeProjectUi() {
  val scaffoldState = rememberScaffoldState()

  Scaffold(
    scaffoldState = scaffoldState,

    //top bar
    topBar = {
      KTopAppBar(scaffoldState = scaffoldState, title = "Positive or Negative")
    },

    // drawer
    drawerContent = {
      KDrawer {}
    }
  ) {
    PositiveNegativeProject(
      modifier = Modifier.padding(8.dp),
    )
  }
}