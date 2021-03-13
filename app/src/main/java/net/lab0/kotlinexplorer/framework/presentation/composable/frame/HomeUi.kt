package net.lab0.kotlinexplorer.framework.presentation.composable.frame

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.rememberNavController
import net.lab0.kotlinexplorer.framework.presentation.composable.chapter.ChapterUi
import net.lab0.kotlinexplorer.framework.presentation.composable.lesson.LessonUi


sealed class HomeScreen(
  val routeDefinition: String,
) {
  object Learn : HomeScreen(
    routeDefinition = "Lesson?id={id}"
  ) {
    fun route(id: String) =
      routeDefinition.replace("{id}", id)
  }

  object Chapters : HomeScreen(routeDefinition = "Chapters")
  object Profile : HomeScreen(routeDefinition = "Profile")
  object Tools : HomeScreen(routeDefinition = "Tools")
}

@Composable
fun HomeUi(
  viewModelFactory: ViewModelProvider.Factory
) {
  Surface(color = MaterialTheme.colors.background) {
    val navController = rememberNavController()
    NavHost(navController, startDestination = HomeScreen.Chapters.routeDefinition) {
      composable(HomeScreen.Chapters.routeDefinition) {
        ChapterUi(navController, viewModelFactory)
      }

      composable(
        HomeScreen.Learn.routeDefinition,
        arguments = listOf(navArgument("id") { }),
      ) {
        LessonUi(navController, viewModelFactory, lessonId = it.arguments?.getString("id")!!)
      }
    }
  }
}
