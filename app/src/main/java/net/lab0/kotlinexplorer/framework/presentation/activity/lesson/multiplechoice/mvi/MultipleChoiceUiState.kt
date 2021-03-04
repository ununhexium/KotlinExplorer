package net.lab0.kotlinexplorer.framework.presentation.activity.lesson.multiplechoice.mvi

import net.lab0.kotlinexplorer.business.domain.Chapter
import net.lab0.kotlinexplorer.business.domain.LessonPage
import net.lab0.kotlinexplorer.framework.presentation.composable.lesson.MultipleChoiceAnswer
import net.lab0.kotlinexplorer.mvi.UiState

data class MultipleChoiceUiState(
  val pageIndex: Int,
  val lessonPage: LessonPage.MultipleChoice,
  val chapter: Chapter,
  val selectedAnswers: List<Int> = listOf(),
  val locked: Boolean = false,
) : UiState {

  fun lockableCopy(
    lessonPage: LessonPage.MultipleChoice? = null,
    selectedAnswers: List<Int>? = null,
  ) = if (!locked) {
    MultipleChoiceUiState(
      pageIndex = this.pageIndex,
      lessonPage = lessonPage ?: this.lessonPage,
      chapter = this.chapter,
      selectedAnswers = selectedAnswers ?: this.selectedAnswers,
      locked = this.locked,
    )
  } else this

  private val TAG = this::class.java.canonicalName

  val choices: List<MultipleChoiceAnswer> =
    lessonPage.choices.mapIndexed { index, it ->
      MultipleChoiceAnswer(
        id = index,
        text = it,
        used = index in selectedAnswers,
        correct = if (locked) {
          when{
            index in selectedAnswers && index in lessonPage.answer -> true
            index !in selectedAnswers && index !in lessonPage.answer -> true
            else -> false
          }
        } else null
      )
    }

  val isCorrectAnswer = selectedAnswers.toSet() == lessonPage.answer
  val showAnswer = locked
  val progress: Float = 1f * pageIndex / chapter.lessons.size
}
