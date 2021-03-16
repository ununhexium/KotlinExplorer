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
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.navigate
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.google.firebase.auth.FirebaseAuth
import net.lab0.kotlinexplorer.BuildConfig
import net.lab0.kotlinexplorer.framework.presentation.composable.lesson.LessonsNav
import net.lab0.kotlinexplorer.framework.presentation.composable.login.LoginUi


sealed class TopLevelScreen(
  val routeDefinition: String,
  val icon: ImageVector,
  val name: String,
) {
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

sealed class ToolScreens(val routeDefinition: String) {
  object List : ToolScreens("List")
  object IntVisualizer : ToolScreens("IntVisualizer?number={number}") {
    fun route(number: Long) =
      routeDefinition.replace("{number}", number.toString())
  }

  object FloatVisualizer : ToolScreens("FloatVisualizer?number={number}") {
    fun route(number: Float) =
      routeDefinition.replace("{number}", number.toString())
  }
}

@Composable
fun HomeNav(
  viewModelFactory: ViewModelProvider.Factory
) {
  Surface(color = MaterialTheme.colors.background) {
    val topLevelNavController = rememberNavController()
    val auth = FirebaseAuth.getInstance()

    val startDestination = if (auth.currentUser == null) {
      TopLevelScreen.Login.routeDefinition
    } else {
      TopLevelScreen.Chapters.routeDefinition
    }

    val debugStart = if (BuildConfig.DEBUG) {
      TopLevelScreen.Tools.routeDefinition
    } else startDestination

    NavHost(
      navController = topLevelNavController,
      startDestination = debugStart
    ) {
      composable(
        TopLevelScreen.Chapters.routeDefinition,
        arguments = listOf(navArgument("id") { }),
      ) {
        LessonsNav(topLevelNavController, viewModelFactory)
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
        UserProfileUi(topLevelNavController)
      }

      navigation(
        startDestination = ToolScreens.List.routeDefinition,
        route = TopLevelScreen.Tools.routeDefinition
      ) {
        composable(
          ToolScreens.List.routeDefinition
        ) {
          ToolsUi(topLevelNavController)
        }

        composable(
          ToolScreens.IntVisualizer.routeDefinition,
          arguments = listOf(navArgument("number") {
            type = NavType.LongType
            defaultValue = 0L
          }),
        ) { backStackEntry ->
          val byte = backStackEntry.arguments?.getLong("number")!!.toByte()

          Int8VisualizerUi(topLevelNavController, initialByte = byte)
        }

        composable(
          ToolScreens.FloatVisualizer.routeDefinition,
          arguments = listOf(navArgument("number") {
            type = NavType.FloatType
            defaultValue = 0f
          }),
        ) { backStackEntry ->
          val float = backStackEntry.arguments?.getFloat("number")!!

          FloatingPointVisualizerUi(topLevelNavController, float)
        }
      }
    }
  }
}
