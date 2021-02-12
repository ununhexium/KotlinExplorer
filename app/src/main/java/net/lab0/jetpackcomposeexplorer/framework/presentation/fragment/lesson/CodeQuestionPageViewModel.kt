package net.lab0.jetpackcomposeexplorer.framework.presentation.fragment.lesson

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import net.lab0.jetpackcomposeexplorer.findAndEdit
import net.lab0.jetpackcomposeexplorer.business.domain.KotlinCodeWithBlanksImpl
import net.lab0.jetpackcomposeexplorer.business.domain.LessonPage
import net.lab0.jetpackcomposeexplorer.framework.presentation.components.code.Answer
import net.lab0.jetpackcomposeexplorer.utils.cached
import org.commonmark.parser.Parser

class CodeQuestionPageViewModel(
    question: String,
    initialSnippet: String,
    val explanation: String,
    val maxAnswers: Int,
    choices: List<Answer>,
    val answerValidator: (List<Answer>) -> Boolean = { false },
) {
  constructor(page: LessonPage.CodeQuestionPage, shuffleAnswers: Boolean = true) : this(
      page.question,
      page.snippet,
      page.explanation,
      KotlinCodeWithBlanksImpl(page.snippet).placeholderIds.distinct().size,
      page.choices
          .mapIndexed { index, s -> Answer(index, s, false) }
          .let { if (shuffleAnswers) it.shuffled() else it },
      { answers -> page.answer == answers.map { it.text } }
  )

  constructor(
      question: String,
      codeSample: String,
      explanation: String,
      maxAnswers: Int,
      vararg choices: String,
      answerValidator: (List<Answer>) -> Boolean,
  ) : this(
      question,
      codeSample,
      explanation,
      maxAnswers,
      choices.mapIndexed { index, s -> Answer(index, s, false) },
      answerValidator
  )

  constructor(
      question: String,
      codeSample: String,
      explanation: String,
      maxAnswers: Int,
      vararg choices: Answer,
      answerValidator: (List<Answer>) -> Boolean,
  ) : this(
      question,
      codeSample,
      explanation,
      maxAnswers,
      choices.toList(),
      answerValidator
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

  val answers: MutableState<List<Answer>> = mutableStateOf(choices)
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

  fun select(answer: Answer) {
    if (selected.value.size >= maxAnswers) return // can't select more

    answers.findAndEdit(answer) {
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
    answers.findAndEdit(last) {
      it.copy(used = false)
    }
  }

  fun reset() {
    selected.value = mutableSetOf()
    answers.value = answers.value.map { it.copy(used = false) }
  }

  fun isCorrectAnswer(): Boolean {
    return answerValidator(selected.value.toList())
  }

  fun validate() {
    showAnswer.value = true
  }
}
