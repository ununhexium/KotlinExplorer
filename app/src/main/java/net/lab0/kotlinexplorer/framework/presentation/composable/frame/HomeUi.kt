package net.lab0.kotlinexplorer.framework.presentation.composable.frame

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.rememberNavController
import net.lab0.kotlinexplorer.framework.presentation.composable.lesson.ChaptersUi


sealed class HomeScreen(
  val routeDefinition: String,
) {
  object Chapters : HomeScreen(routeDefinition = "Chapters")
  object Profile : HomeScreen(routeDefinition = "Profile")
  object Login : HomeScreen(routeDefinition = "Login")
  object Tools : HomeScreen(routeDefinition = "Tools")
}

@Composable
fun HomeUi(
  viewModelFactory: ViewModelProvider.Factory
) {
  Surface(color = MaterialTheme.colors.background) {
    val navController = rememberNavController()
    NavHost(navController, startDestination = HomeScreen.Chapters.routeDefinition) {
      composable(
        HomeScreen.Tools.routeDefinition
      ) {
        Text("Tools")
      }

      composable(
        HomeScreen.Chapters.routeDefinition,
        arguments = listOf(navArgument("id") { }),
      ) {
        ChaptersUi(navController, viewModelFactory)
      }
    }
  }
}
