package net.lab0.kotlinexplorer.framework.presentation.composable.lesson

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.navigate
import net.lab0.kotlinexplorer.business.domain.Lesson
import net.lab0.kotlinexplorer.business.domain.LessonBrowser
import net.lab0.kotlinexplorer.business.domain.LessonPage
import net.lab0.kotlinexplorer.framework.presentation.activity.lesson.AnswerCorrectness
import net.lab0.kotlinexplorer.framework.presentation.activity.lesson.codequestion.CodeQuestionViewModel
import net.lab0.kotlinexplorer.framework.presentation.activity.lesson.multiplechoice.MultipleChoiceViewModel
import net.lab0.kotlinexplorer.framework.presentation.activity.lesson.mvi.LessonViewModel
import net.lab0.kotlinexplorer.framework.presentation.composable.code.CodeQuizPage2
import net.lab0.kotlinexplorer.framework.presentation.composable.frame.topbar.KTopAppBar
import net.lab0.kotlinexplorer.utils.Do
import net.lab0.kotlinexplorer.utils.printLogD

@Composable
fun LessonPageUi(
  navController: NavHostController,
  lessonViewModel: LessonViewModel,
  lesson: Lesson,
  pageIndex: Int,
) {
  val chapter = LessonBrowser.getChapterForLesson(lesson.id)!!
  val progress = 1f * pageIndex / lesson.pages.size
  val page = lesson.pages[pageIndex]

  val scaffoldState = rememberScaffoldState()

  Scaffold(
    scaffoldState = scaffoldState,
    topBar = {
      KTopAppBar(scaffoldState, lesson.title)
    },
    drawerContent = {
      LessonDrawer(
        navController = navController,
        chapter = chapter.title,
        lesson = lesson,
        currentPage = page,
        modifier = Modifier
          .padding(8.dp)
          .fillMaxWidth(),
      ) { page ->
        // skip to page
        navController.navigate(
          LessonScreen.LessonPage.route(
            lesson.id, lesson.pages.indexOfFirst { it == page }
          )
        )
      }
    }
  ) {
    Column(
      modifier = Modifier.fillMaxSize(),
      verticalArrangement = Arrangement.SpaceBetween
    ) {
      LessonPageHeader(
        title = page.title,
      )

      val nextPage = {
        val nextPageIndex = pageIndex + 1
        println("Next page: $nextPageIndex")
        if (nextPageIndex >= lesson.pages.size) {
          lessonViewModel.saveLesson()
          navController.navigate(
            LessonScreen.NextLesson.route(lesson.id)
          )
        } else {
          printLogD(
            className = "LessonPageUi",
            message = "Navigate to ${lesson.id}, $nextPageIndex"
          )
          navController.navigate(
            LessonScreen.LessonPage.route(lesson.id, nextPageIndex)
          )
        }
      }

      Do exhaustive when (page) {
        is LessonPage.CodeQuestionPage -> {
          val model: CodeQuestionViewModel = viewModel()
          model.init(pageIndex, page, chapter)

          CodeQuizPage2(
            onNextPage = { correctness ->
              lessonViewModel.countMark(page, correctness)

              nextPage()
            }
          )
        }

        is LessonPage.InfoPage -> {
          InfoLessonPage(
            markdownAsString = page.markdown,
            onNextPage = {
              lessonViewModel.countMark(page, AnswerCorrectness.NEUTRAL)

              nextPage()
            }
          )
        }

        is LessonPage.MultipleChoice -> {
          val model: MultipleChoiceViewModel = viewModel()
          model.init(pageIndex, page, chapter)
          MultipleChoiceUi(
            onNextPage = { correctness ->
              lessonViewModel.countMark(page, correctness)

              nextPage()
            }
          )
        }
      }

      LinearProgressIndicator(
        progress = if (progress.isFinite()) progress else 0f,
        modifier = Modifier
          .fillMaxWidth()
          .height(4.dp)
      )
    }
  }
}
