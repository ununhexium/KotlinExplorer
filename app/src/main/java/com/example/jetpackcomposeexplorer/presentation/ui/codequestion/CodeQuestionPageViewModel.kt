package com.example.jetpackcomposeexplorer.presentation.ui.codequestion

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.example.jetpackcomposeexplorer.findAndEdit
import com.example.jetpackcomposeexplorer.model.KotlinCodeWithBlanksImpl
import com.example.jetpackcomposeexplorer.model.course.LessonPage
import com.example.jetpackcomposeexplorer.presentation.components.code.Answer
import org.commonmark.parser.Parser

class CodeQuestionPageViewModel(
    question: String,
    initialSnippet: String,
    explanation: String,
    val maxAnswers: Int,
    choices: List<Answer>,
    val answerValidator: (List<Answer>) -> Boolean = { false },
) {
  constructor(page: LessonPage.CodeQuestionPage, shuffleAnswers: Boolean = true) : this(
      page.question,
      page.snippet,
      page.answer,
      KotlinCodeWithBlanksImpl(page.snippet).placeholderIds.size,
      page.choices
          .mapIndexed { index, s -> Answer(index, s, false) }
          .let { if (shuffleAnswers) it.shuffled() else it },
      { answers -> page.answerValidator(answers.map { it.text }) }
  )

  constructor(
      question: String,
      codeSample: String,
      answer: String,
      maxAnswers: Int,
      vararg choices: String,
      answerValidator: (List<Answer>) -> Boolean,
  ) : this(
      question,
      codeSample,
      answer,
      maxAnswers,
      choices.mapIndexed { index, s -> Answer(index, s, false) },
      answerValidator
  )

  constructor(
      question: String,
      codeSample: String,
      answer: String,
      maxAnswers: Int,
      vararg choices: Answer,
      answerValidator: (List<Answer>) -> Boolean,
  ) : this(
      question,
      codeSample,
      answer,
      maxAnswers,
      choices.toList(),
      answerValidator
  )

  val questionMarkdown = Parser.builder().build().parse(question)
  val explanationMarkdown = Parser.builder().build().parse(explanation)

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
