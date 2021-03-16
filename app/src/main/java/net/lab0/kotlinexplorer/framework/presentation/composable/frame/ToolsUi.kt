package net.lab0.kotlinexplorer.framework.presentation.composable.frame

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.navigate
import androidx.navigation.compose.rememberNavController

@Composable
fun ToolsUi(
  navHostController: NavHostController,
) {
  val scaffoldState = rememberScaffoldState()

  TopLevelScaffold(
    navController = navHostController,
    title = "Tools",
    scaffoldState = scaffoldState,
    quickScreens = listOf(TopLevelScreen.Chapters, TopLevelScreen.Tools),
  ) {
    Column(modifier = Modifier.fillMaxWidth()) {
      val modifier = Modifier
        .padding(vertical = 8.dp)
        .align(Alignment.CenterHorizontally)
      Button(
        modifier = modifier,
        onClick = {
          navHostController.navigate(ToolScreens.IntVisualizer.routeDefinition)
        }) {
        Text("Integer explorer")
      }
      Button(
        modifier = modifier,
        onClick = {
          navHostController.navigate(ToolScreens.FloatVisualizer.routeDefinition)
        },
      ) {
        Text("Floating point explorer")
      }
    }
  }
}

@Preview
@Composable
private fun ToolsUiPreview() {
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
          ToolsUi(rememberNavController())
        }
      }
    }
  }
}
