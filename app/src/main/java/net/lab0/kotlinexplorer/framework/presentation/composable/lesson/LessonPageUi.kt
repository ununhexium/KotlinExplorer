package net.lab0.kotlinexplorer.framework.presentation.composable.lesson

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.navigate
import net.lab0.kotlinexplorer.business.domain.Lesson
import net.lab0.kotlinexplorer.business.domain.LessonBrowser
import net.lab0.kotlinexplorer.business.domain.LessonPage
import net.lab0.kotlinexplorer.framework.presentation.activity.lesson.codequestion.CodeQuestionViewModel
import net.lab0.kotlinexplorer.framework.presentation.activity.lesson.multiplechoice.MultipleChoiceViewModel
import net.lab0.kotlinexplorer.framework.presentation.composable.code.CodeQuizPage2
import net.lab0.kotlinexplorer.utils.Do

@Composable
fun LessonPageUi(
  navController: NavHostController,
  lesson: Lesson,
  pageIndex: Int,
  onBack: () -> Unit,
  onProblemReport: () -> Unit,
) {
  val chapter = LessonBrowser.getChapterForLesson(lesson.id)!!
  val progress = 1f * pageIndex / lesson.pages.size
  val page = lesson.pages[pageIndex]

  Scaffold(
    drawerContent = {
      LessonDrawer(
        chapter = chapter.title,
        lesson = lesson.title,
        lessonPages = lesson.pages.map { it.title },
        currentPage = page.title,
      ) { title ->
        // skip to page
      }
    }
  ) {

    Column(
      modifier = Modifier.fillMaxSize(),
      verticalArrangement = Arrangement.SpaceBetween
    ) {
      LessonPageHeader(
        title = page.title,
        backAction = onBack,
        reportMistakeAction = onProblemReport
      )

      val nextPage = {
        val nextPageIndex = pageIndex + 1
        println("Next page: $nextPageIndex")
        if (nextPageIndex >= lesson.pages.size) {
          navController.navigate(
            LessonScreen.NextLesson.route(lesson.id)
          )
        } else {
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
            nextQuestion = nextPage
          )
        }

        is LessonPage.InfoPage -> {
          InfoLessonPage(
            markdownAsString = page.markdown,
            nextPage = nextPage
          )
        }

        is LessonPage.MultipleChoice -> {
          val model: MultipleChoiceViewModel = viewModel()
          model.init(pageIndex, page, chapter)
          MultipleChoiceUi(
            onNextPage = nextPage
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
