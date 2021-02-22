package net.lab0.kotlinexplorer.framework.presentation.activity.lesson.codequestion.mvi

import net.lab0.kotlinexplorer.business.domain.Chapter
import net.lab0.kotlinexplorer.business.domain.LessonPage
import net.lab0.kotlinexplorer.business.domain.parser.KotlinCodeWithBlanksImpl
import net.lab0.kotlinexplorer.framework.presentation.composable.code.Answer
import net.lab0.kotlinexplorer.mvi.UiState

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
        pageIndex = this.pageIndex,
        lessonPage = lessonPage ?: this.lessonPage,
        chapter = this.chapter,
        selectedAnswers = selectedAnswers ?: this.selectedAnswers,
        locked = this.locked,
    )
  } else this

  val choices: List<Answer> =
      lessonPage.choices.mapIndexed { index, it ->
        Answer(index, it, index in selectedAnswers)
      }

  val canUndoOrReset = selectedAnswers.isNotEmpty()
  val canValidate = selectedAnswers.size == lessonPage.answer.size
  val isCorrectAnswer = selectedAnswers.map { lessonPage.choices[it] } == lessonPage.answer
  val showAnswer = locked
  val nextBlank: Int = selectedAnswers.size
  val progress: Float = 1f * pageIndex / chapter.lessons.size
  val snippet: String = KotlinCodeWithBlanksImpl(
      lessonPage.snippet
  ).fill(
      selectedAnswers.mapIndexed { index, it -> index to lessonPage.choices[it] }.toMap()
  )
}
