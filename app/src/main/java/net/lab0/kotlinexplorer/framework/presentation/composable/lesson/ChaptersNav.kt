package net.lab0.kotlinexplorer.framework.presentation.composable.lesson

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltNavGraphViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.navigate
import androidx.navigation.compose.popUpTo
import androidx.navigation.compose.rememberNavController
import net.lab0.kotlinexplorer.business.domain.LessonBrowser
import net.lab0.kotlinexplorer.framework.presentation.activity.lesson.mvi.LessonViewModel
import net.lab0.kotlinexplorer.framework.presentation.composable.chapter.ChapterUi
import net.lab0.kotlinexplorer.framework.presentation.composable.fakeFactory
import net.lab0.kotlinexplorer.framework.presentation.composable.fakeGetAllChapters
import net.lab0.kotlinexplorer.framework.presentation.composable.fakeGetLessonsInProgress
import net.lab0.kotlinexplorer.framework.presentation.composable.problemreport.ProblemReportUi
import net.lab0.kotlinexplorer.framework.presentation.fragment.chapterlist.ChapterListViewModel
import net.lab0.kotlinexplorer.framework.ui.theme.KotlinExplorerTheme

sealed class LessonScreen(
  val routeDefinition: String,
) {
  object Chapters : LessonScreen("Chapters")

  object Introduction : LessonScreen(
    "Introduction?lessonId={lessonId}"
  ) {
    fun route(lessonId: String) =
      routeDefinition
        .replace("{lessonId}", lessonId)
  }

  object LessonPage : LessonScreen(
    "Information?lessonId={lessonId}&pageIndex={pageIndex}"
  ) {
    fun route(lessonId: String, pageIndex: Int) =
      routeDefinition
        .replace("{lessonId}", lessonId)
        .replace("{pageIndex}", pageIndex.toString())
  }

  object NextLesson : LessonScreen("NextLesson?lessonId={lessonId}") {
    fun route(lessonId: String) =
      routeDefinition
        .replace("{lessonId}", lessonId)
  }

  object ProblemReport : LessonScreen("ProblemReport?lessonId={lessonId}&pageIndex={pageIndex}") {
    fun route(lessonId: String, pageIndex: Int) =
      routeDefinition
        .replace("{lessonId}", lessonId)
        .replace("{pageIndex}", pageIndex.toString())
  }

  object ExtraLessonsRequest : LessonScreen("ExtraLessonsRequest")
}

@Composable
fun ChaptersNav(
  topLevelNavController: NavHostController,
  viewModelFactory: ViewModelProvider.Factory,
) {
  KotlinExplorerTheme {
    val navController = rememberNavController()

    NavHost(navController, startDestination = LessonScreen.Chapters.routeDefinition) {

      // Chapters
      composable(LessonScreen.Chapters.routeDefinition) {
        hiltNavGraphViewModel<LessonViewModel>()

        ChapterUi(topLevelNavController, navController, viewModelFactory)
      }

      // Extra Lessons Request
      composable(LessonScreen.ExtraLessonsRequest.routeDefinition) {
        ExtraContentRequestUi(topLevelNavController)
      }

      // Introduction
      composable(
        LessonScreen.Introduction.routeDefinition,
        arguments = listOf(
          navArgument("lessonId") {
            type = NavType.StringType
          },
          navArgument("pageIndex") {
            defaultValue = 0
            type = NavType.IntType
          },
        ),
      ) { backStackEntry ->
        val lesson = LessonBrowser.getLessonById(
          backStackEntry.arguments?.getString("lessonId")!!
        )

        LessonIntroductionPageUi(navController, lesson)
      }

      // LessonPage
      composable(
        LessonScreen.LessonPage.routeDefinition,
        arguments = listOf(
          navArgument("lessonId") {
            type = NavType.StringType
          },
          navArgument("pageIndex") {
            defaultValue = 0
            type = NavType.IntType
          },
        ),
      ) { backStackEntry ->
        val lessonId = backStackEntry.arguments?.getString("lessonId")!!

        val lesson = LessonBrowser.getLessonById(lessonId)

        val pageIndex = backStackEntry.arguments?.getInt("pageIndex")!!
        println("pageIndex $pageIndex")

        val lessonViewModel: LessonViewModel =
          navController.hiltNavGraphViewModel(LessonScreen.Chapters.routeDefinition)

        if (pageIndex == 0) {
          lessonViewModel.init(lesson)
        }

        LessonPageUi(
          navController = navController,
          lessonViewModel = lessonViewModel,
          lesson = lesson,
          pageIndex = pageIndex,
        )
      }

      // NextLesson
      composable(
        LessonScreen.NextLesson.routeDefinition,
        arguments = listOf(
          navArgument("lessonId") {
            type = NavType.StringType
          },
        ),
      ) { backStackEntry ->
        val lessonId = backStackEntry.arguments?.getString("lessonId")!!

        val nextLesson = LessonBrowser.getNextLessonInChapter(lessonId)

        NextLessonPage(
          goToChapters = {
            navController.navigate(LessonScreen.Chapters.routeDefinition) {
              popUpTo(LessonScreen.Chapters.routeDefinition) {
                inclusive = true
              }
            }
          },
          nextLesson = nextLesson
        ) {
          nextLesson?.let {
            navController.navigate(LessonScreen.Introduction.route(nextLesson.id)) {
              popUpTo(LessonScreen.Chapters.routeDefinition) {
                inclusive = false
              }
            }
          }
        }
      }

      // ProblemReport
      composable(
        LessonScreen.ProblemReport.routeDefinition,
        arguments = listOf(
          navArgument("lessonId") {
            type = NavType.StringType
          },
          navArgument("pageIndex") {
            defaultValue = 0
            type = NavType.IntType
          },
        ),
      ) { backStackEntry ->
        val lessonId = backStackEntry.arguments?.getString("lessonId")!!
        val lesson = LessonBrowser.getLessonById(lessonId)

        val pageIndex = backStackEntry.arguments?.getInt("pageIndex")!!
        val page = lesson.pages[pageIndex]

        val context = LocalContext.current

        val lessonViewModel: LessonViewModel =
          navController.hiltNavGraphViewModel(
            LessonScreen.Chapters.routeDefinition
          )

        ProblemReportUi(
          problemLocation = "lessonId = ${lesson.id}, page=${page.title}",
          submitReport = {
            lessonViewModel.onProblemReport(it, context)
          },
          onCloseProblemReport = {
            navController.popBackStack()
          },
        )
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
          ChaptersNav(
            rememberNavController(),
            fakeFactory {
              ChapterListViewModel(
                fakeGetLessonsInProgress(),
                fakeGetAllChapters()
              )
            },
          )
        }
      }
    }
  }
}
