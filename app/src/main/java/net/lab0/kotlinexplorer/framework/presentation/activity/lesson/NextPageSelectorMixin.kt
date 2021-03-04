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
    val lessonPage = LessonBrowser.getLessonById(lessonId).pages[page]
    val lastLessonInChapter = LessonBrowser.getNextLessonInChapter(lessonId) == null

    activityViewModel.countMark(
        lessonPage = lessonPage,
        correctness = correctness
    )

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
              if(lastLessonInChapter) {
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
