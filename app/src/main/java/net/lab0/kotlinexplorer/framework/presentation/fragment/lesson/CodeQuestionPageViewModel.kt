package net.lab0.kotlinexplorer.framework.presentation.fragment.lesson

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import net.lab0.kotlinexplorer.findAndEdit
import net.lab0.kotlinexplorer.business.domain.parser.KotlinCodeWithBlanksImpl
import net.lab0.kotlinexplorer.business.domain.LessonPage
import net.lab0.kotlinexplorer.framework.presentation.composable.code.Answer
import net.lab0.kotlinexplorer.utils.cached
import org.commonmark.parser.Parser

class CodeQuestionPageViewModel(
    question: String,
    initialSnippet: String,
    val explanation: String,
    val maxAnswers: Int,
    choices: List<Answer>,
    val correctAnswer: List<String>,
    val answerValidator: (List<Answer>) -> Boolean = { false },
) {
  constructor(page: LessonPage.CodeQuestionPage, shuffleAnswers: Boolean = true) : this(
      page.question,
      page.snippet,
      page.explanation,
      KotlinCodeWithBlanksImpl(page.snippet).placeholderIds.distinct().size,
      (page.answer + page.confusion)
          .mapIndexed { index, s -> Answer(index, s, false) }
          .let { if (shuffleAnswers) it.shuffled() else it },
      page.answer,
      { answers -> page.answer == answers.map { it.text } },
  )

  /**
   * For tests and debug
   */
  constructor(
      question: String,
      codeSample: String,
      explanation: String,
      correctAnswer: List<String>,
      confusion: List<String> = listOf(),
  ) : this(
      question,
      codeSample,
      explanation,
      correctAnswer.size,
      (correctAnswer + confusion).mapIndexed { index, s -> Answer(index, s, false) },
      correctAnswer,
      { answers -> correctAnswer == answers.map { it.text } }
  )

  val questionMarkdown = Parser.builder().build().parse(question)
  val explanationMarkdown by cached(
      input = { selected.value },
      transform = {
        Parser.builder().build().parse(
            explanation
        )
      }
  )

  val possibleAnswers: MutableState<List<Answer>> = mutableStateOf(choices)
  val selected: MutableState<Set<Answer>> = mutableStateOf(setOf())
  val showAnswer = mutableStateOf(false)

  private val withBlanks = KotlinCodeWithBlanksImpl(initialSnippet)

  val canUndoOrReset
    get() = selected.value.isNotEmpty() && !showAnswer.value

  val canValidate
    get() = selected.value.size == maxAnswers

  val snippet: String
    get() = withBlanks.fill(
        selected.value.mapIndexed { index, answer ->
          index to answer.text
        }.toMap()
    )

  val answerSnippet: String
    get() = withBlanks.fill(
        correctAnswer.mapIndexed { index, answer ->
          index to answer
        }.toMap()
    )

  val nextBlank: Int?
    get() = if(canValidate) null else selected.value.size

  fun select(answer: Answer) {
    if (selected.value.size >= maxAnswers) return // can't select more

    possibleAnswers.findAndEdit(answer) {
      it.copy(used = true)
    }
    selected.value = selected.value.toMutableSet().also {
      it.add(answer.copy(used = true))
    }
  }

  fun undo() {
    if (!canUndoOrReset) return

    val last = selected.value.last()
    selected.value = selected.value.toMutableSet().also { it.remove(last) }
    possibleAnswers.findAndEdit(last) {
      it.copy(used = false)
    }
  }

  fun reset() {
    selected.value = mutableSetOf()
    possibleAnswers.value = possibleAnswers.value.map { it.copy(used = false) }
  }

  fun isCorrectAnswer(): Boolean {
    return answerValidator(selected.value.toList())
  }

  fun validate() {
    showAnswer.value = true
  }
}
