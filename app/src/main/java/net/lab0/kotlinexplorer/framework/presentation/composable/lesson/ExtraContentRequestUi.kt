package net.lab0.kotlinexplorer.framework.presentation.composable.lesson

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltNavGraphViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.navigate
import androidx.navigation.compose.popUpTo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import net.lab0.kotlinexplorer.framework.presentation.activity.lesson.mvi.LessonViewModel
import net.lab0.kotlinexplorer.framework.presentation.composable.morelessons.ExtraContentRequest

@Composable
fun ExtraContentRequestUi(navController: NavHostController) {
  val model: LessonViewModel =
    navController.hiltNavGraphViewModel(LessonScreen.Chapters.routeDefinition)

  val context = LocalContext.current

  val coro = rememberCoroutineScope()

  ExtraContentRequest(
    onValidate = { liking, whyMore, comment ->

      model.request(liking, whyMore, comment) {
        coro.launch(Dispatchers.Main) {
          navController.navigate(LessonScreen.Chapters.routeDefinition) {
            popUpTo(LessonScreen.Chapters.routeDefinition) {
              inclusive = true
            }
          }
        }
      }

      Toast.makeText(context, "We'll work on that! :)", Toast.LENGTH_LONG).show()
    }
  )
}
