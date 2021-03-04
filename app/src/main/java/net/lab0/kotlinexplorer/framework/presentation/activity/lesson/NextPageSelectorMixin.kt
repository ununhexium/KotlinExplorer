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
  ): () -> Unit {
    if(page >= 0) {
      val lessonPage = LessonBrowser.getLessonById(lessonId).pages[page]

      activityViewModel.countMark(
        lessonPage = lessonPage,
        correctness = correctness
      )
    }

    val isLastLessonInChapter = LessonBrowser.getNextLessonInChapter(lessonId) == null

    val nextPageIndex = page + 1
    val maybeNextPage = LessonBrowser.getLessonById(lessonId)
        .pages
        .getOrNull(nextPageIndex)

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
            is LessonPage.InfoPage -> navigationToInfo(lessonId, nextPageIndex)
            is LessonPage.CodeQuestionPage -> navigationToCodeQuestion(lessonId, nextPageIndex)
            is LessonPage.MultipleChoice -> navigationToMultipleChoice(lessonId, nextPageIndex)
          }
      )
    }
  }
}
