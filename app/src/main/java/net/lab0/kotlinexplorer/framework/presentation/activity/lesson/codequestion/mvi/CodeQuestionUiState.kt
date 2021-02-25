package net.lab0.kotlinexplorer.framework.presentation.activity.lesson.codequestion.mvi

import androidx.compose.ui.text.AnnotatedString
import net.lab0.kotlinexplorer.business.domain.Chapter
import net.lab0.kotlinexplorer.business.domain.LessonPage
import net.lab0.kotlinexplorer.business.domain.extractHighlightsAndAnnotate
import net.lab0.kotlinexplorer.business.domain.parser.KotlinCodeWithBlanksImpl
import net.lab0.kotlinexplorer.framework.presentation.composable.code.Answer
import net.lab0.kotlinexplorer.framework.presentation.composable.code.DefaultCodeStyle
import net.lab0.kotlinexplorer.framework.presentation.composable.code.invertForegroundBackgroundColors
import net.lab0.kotlinexplorer.mvi.UiState

data class CodeQuestionUiState(
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
    CodeQuestionUiState(
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

  val selectedAnswersSnippet: String = KotlinCodeWithBlanksImpl(
      lessonPage.snippet
  ).fill(
      selectedAnswers.mapIndexed { index, it -> index to lessonPage.choices[it] }.toMap()
  )

  /**
   * The locations of the correct answers on the correct code
   * where the wrong answer was given.
   */
  val correctedAnswersLocations: List<IntRange> by lazy {
    val correctAnswers = lessonPage.answer.mapIndexed { index, it -> index to it }
    val choicesAsStrings = selectedAnswers.map { lessonPage.choices[it] }
    val wrongAnswers = choicesAsStrings
        .zip(correctAnswers)
        .filter { it.first != it.second.second }
        .map { it.second.first }

    KotlinCodeWithBlanksImpl(lessonPage.snippet)
        .getRealStringIndices(correctAnswers.toMap())
        .filterKeys { it in wrongAnswers }
        .values
        .toList()
        .flatten()
  }

  val snippetWithFocusedMistakes: AnnotatedString by lazy {
    val annotated = extractHighlightsAndAnnotate(
        lessonPage.answerSnippet,
        DefaultCodeStyle.textStyler
    )
    correctedAnswersLocations.fold(annotated) { acc, e ->
      acc.invertForegroundBackgroundColors(e)
    }
  }
}
