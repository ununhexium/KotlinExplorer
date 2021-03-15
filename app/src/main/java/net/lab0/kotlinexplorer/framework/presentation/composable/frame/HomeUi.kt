package net.lab0.kotlinexplorer.framework.presentation.composable.frame

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Login
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.navigate
import androidx.navigation.compose.rememberNavController
import com.google.firebase.auth.FirebaseAuth
import net.lab0.kotlinexplorer.framework.presentation.composable.ToolsUi
import net.lab0.kotlinexplorer.framework.presentation.composable.lesson.LessonsUi
import net.lab0.kotlinexplorer.framework.presentation.composable.login.LoginUi


sealed class TopLevelScreen(
  val routeDefinition: String,
  val icon: ImageVector,
  val name: String,
) {
  companion object{
    val scaffoldScreens = listOf(Chapters, Tools)
  }

  object Chapters : TopLevelScreen(
    routeDefinition = "Chapters",
    icon = Icons.Default.MenuBook,
    name = "Chapters"
  )
  object Profile : TopLevelScreen(
    routeDefinition = "Profile",
    icon = Icons.Default.AccountBox,
    name = "Profile"
  )
  object Login : TopLevelScreen(
    routeDefinition = "Login",
    icon = Icons.Default.Login,
    name = "Login"
  )
  object Tools : TopLevelScreen(
    routeDefinition = "Tools",
    icon = Icons.Default.Build,
    name = "Tools"
  )
}

@Composable
fun HomeUi(
  viewModelFactory: ViewModelProvider.Factory
) {
  Surface(color = MaterialTheme.colors.background) {
    val topLevelNavController = rememberNavController()
    NavHost(
      navController = topLevelNavController,
      startDestination = TopLevelScreen.Login.routeDefinition
    ) {

      composable(
        TopLevelScreen.Chapters.routeDefinition,
        arguments = listOf(navArgument("id") { }),
      ) {
        LessonsUi(topLevelNavController, viewModelFactory)
      }

      composable(TopLevelScreen.Login.routeDefinition) {
        LoginUi(
          onStartApp = {
            topLevelNavController.navigate(TopLevelScreen.Chapters.routeDefinition)
          },
          loginAnonymously = {
            FirebaseAuth.getInstance().signInAnonymously()
          }
        )
      }

      composable(
        TopLevelScreen.Profile.routeDefinition,
      ) {
        // TODO
      }

      composable(
        TopLevelScreen.Tools.routeDefinition
      ) {
        ToolsUi(topLevelNavController)
      }
    }
  }
}
