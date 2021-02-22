package net.lab0.kotlinexplorer.framework.presentation.fragment.lessonpage.mvi

import net.lab0.kotlinexplorer.business.domain.Chapter
import net.lab0.kotlinexplorer.business.domain.LessonPage
import net.lab0.kotlinexplorer.business.domain.parser.KotlinCodeWithBlanksImpl
import net.lab0.kotlinexplorer.mvi.UiState
import net.lab0.kotlinexplorer.framework.presentation.composable.code.Answer as CompAnswer

data class LessonPageUiState(
    val pageIndex: Int,
    val lessonPage: LessonPage.CodeQuestionPage,
    val chapter: Chapter,
    val selectedAnswers: List<Int> = listOf(),
    val locked: Boolean = false,
) : UiState {

  fun lockableCopy(
      lessonPage: LessonPage.CodeQuestionPage? = null,
      selectedAnswers: List<Int>? = null,
  ) = if (!locked) {
    LessonPageUiState(
        this.pageIndex,
        lessonPage ?: this.lessonPage,
        this.chapter,
        selectedAnswers ?: this.selectedAnswers,
    )
  } else this

  val possibleChoices = lessonPage.answer + lessonPage.confusion

  val choices: List<CompAnswer> =
      possibleChoices.mapIndexed { index, it ->
        CompAnswer(index, it, index in selectedAnswers)
      }

  val canUndoOrReset = selectedAnswers.isNotEmpty()
  val canValidate = selectedAnswers.size == lessonPage.answer.size
  val isCorrectAnswer = selectedAnswers.map { possibleChoices[it] } == lessonPage.answer
  val showAnswer = locked
  val nextBlank: Int = selectedAnswers.size
  val progress: Float = 1f * pageIndex / chapter.lessons.size
  val snippet: String = KotlinCodeWithBlanksImpl(
      lessonPage.snippet
  ).fill(
      selectedAnswers.mapIndexed { index, it -> index to possibleChoices[it] }.toMap()
  )
}
