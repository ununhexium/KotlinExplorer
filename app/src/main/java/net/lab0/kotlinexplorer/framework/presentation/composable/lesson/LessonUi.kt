package net.lab0.kotlinexplorer.framework.presentation.composable.lesson

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.navigate
import androidx.navigation.compose.popUpTo
import androidx.navigation.compose.rememberNavController
import net.lab0.kotlinexplorer.business.course.data.kotlin.helloworld.Introduction
import net.lab0.kotlinexplorer.business.domain.LessonBrowser
import net.lab0.kotlinexplorer.framework.presentation.composable.fakeFactory
import net.lab0.kotlinexplorer.framework.presentation.composable.fakeGetAllChapters
import net.lab0.kotlinexplorer.framework.presentation.composable.fakeGetLessonsInProgress
import net.lab0.kotlinexplorer.framework.presentation.fragment.chapterlist.ChapterListViewModel
import net.lab0.kotlinexplorer.framework.ui.theme.KotlinExplorerTheme

sealed class LessonScreen(
  val routeDefinition: String,
) {
  object Introduction : LessonScreen(
    "Introduction"
  )

  object LessonPage : LessonScreen(
    "Information?pageIndex={pageIndex}"
  ) {
    fun route(pageIndex: Int) =
      routeDefinition.replace("{pageIndex}", pageIndex.toString())
  }

  object NextLesson : LessonScreen("NextLesson")
}

@Composable
fun LessonUi(
  homeNavController: NavHostController,
  viewModelFactory: ViewModelProvider.Factory,
  lessonId: String,
) {
  KotlinExplorerTheme {
    val navController = rememberNavController()
    val lesson = LessonBrowser.getLessonById(lessonId)

    NavHost(navController, startDestination = LessonScreen.Introduction.routeDefinition) {
      composable(LessonScreen.Introduction.routeDefinition) {
        LessonIntroductionPageUi(navController, lesson.title)
      }

      composable(
        LessonScreen.LessonPage.routeDefinition,
        arguments = listOf(
          navArgument("pageIndex") {
            defaultValue = 0
            type = NavType.IntType
          },
        ),
      ) {
        val pageIndex = it.arguments?.getInt("pageIndex")!!
        println("pageIndex $pageIndex")

        if (pageIndex == lesson.pages.size) {
          navController.navigate(LessonScreen.NextLesson.routeDefinition)
        } else {
          LessonPageUi(
            navController = navController,
            lesson = lesson,
            pageIndex = pageIndex,
            onBack = {
              // back to chapter
            },
            onProblemReport = {
              // activityViewModel.onProblemReport(it, requireContext())
            }
          )
        }
      }

      composable(
        LessonScreen.NextLesson.routeDefinition,
      ) {
        val nextLesson = LessonBrowser.getNextLessonInChapter(lessonId)

        NextLessonPage(
          goToChapters = {
            homeNavController.popBackStack()
          },
          nextLesson = nextLesson
        ) {
          nextLesson?.let {
            navController.navigate(LessonScreen.Introduction.routeDefinition) {
              popUpTo(LessonScreen.Introduction.routeDefinition) {
                inclusive = true
              }
            }
          }
        }
      }
    }
  }
}

@Preview
@Composable
private fun LessonUiPreview() {
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
          LessonUi(
            rememberNavController(),
            fakeFactory {
              ChapterListViewModel(
                fakeGetLessonsInProgress(),
                fakeGetAllChapters()
              )
            },
            Introduction.id,
          )
        }
      }
    }
  }
}
