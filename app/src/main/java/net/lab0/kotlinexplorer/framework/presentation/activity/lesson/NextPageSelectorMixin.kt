package net.lab0.kotlinexplorer.framework.presentation.activity.lesson

import androidx.navigation.NavController
import androidx.navigation.NavDirections
import net.lab0.kotlinexplorer.business.domain.LessonBrowser
import net.lab0.kotlinexplorer.business.domain.LessonPage
import net.lab0.kotlinexplorer.framework.presentation.activity.lesson.mvi.LessonViewModel
import net.lab0.kotlinexplorer.utils.Do

interface NextPageSelectorMixin {
  fun nextPage(
    activityViewModel: LessonViewModel,
    correctness: AnswerCorrectness,
    page: Int,
    lessonId: String,
    navController: NavController,
    navigationToFeedback: (String) -> NavDirections,
    navigationToNextChapter: (String) -> NavDirections,
    navigationToInfo: (String, Int) -> NavDirections,
    navigationToMultipleChoice: (String, Int) -> NavDirections,
    navigationToCodeQuestion: (String, Int) -> NavDirections,
    nextPage: Int = page + 1,
  ): () -> Unit {
    if(page >= 0) {
      val lessonPage = LessonBrowser.getLessonById(lessonId).pages[page]

      activityViewModel.countMark(
        lessonPage = lessonPage,
        correctness = correctness
      )
    }

    val isLastLessonInChapter = LessonBrowser.getNextLessonInChapter(lessonId) == null

    val maybeNextPage = LessonBrowser.getLessonById(lessonId)
        .pages
        .getOrNull(nextPage)

    return {
      navController.navigate(
          Do exhaustiveNonNull when (maybeNextPage) {
            null -> {
              // no more pages -> end lesson
              activityViewModel.saveLesson()
              if(isLastLessonInChapter) {
                navigationToFeedback(lessonId)
              }else{
                navigationToNextChapter(lessonId)
              }
            }
            is LessonPage.InfoPage -> navigationToInfo(lessonId, nextPage)
            is LessonPage.CodeQuestionPage -> navigationToCodeQuestion(lessonId, nextPage)
            is LessonPage.MultipleChoice -> navigationToMultipleChoice(lessonId, nextPage)
          }
      )
    }
  }
}
