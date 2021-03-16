package net.lab0.kotlinexplorer.framework.presentation.composable

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import net.lab0.kotlinexplorer.framework.presentation.composable.frame.TopLevelScaffold
import net.lab0.kotlinexplorer.framework.presentation.composable.frame.TopLevelScreen

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
  ){
    Button(onClick = { /*TODO*/ }) {
      Text("Integer explorer")
    }
    Button(onClick = { /*TODO*/ }) {
      Text("Floating point explorer")
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
