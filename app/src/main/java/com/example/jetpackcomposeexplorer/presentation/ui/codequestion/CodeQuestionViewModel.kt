package com.example.jetpackcomposeexplorer.presentation.ui.codequestion

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.example.jetpackcomposeexplorer.findAndEdit
import com.example.jetpackcomposeexplorer.presentation.components.Answer

class CodeQuestionViewModel(
    val question: String,
    val initialCodeSample: String,
    val explanation: String,
    val maxAnswers: Int,
    choices: List<Answer>,
    val answerValidator: (List<Answer>) -> Boolean = { false },
) {
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

  val answers: MutableState<List<Answer>> = mutableStateOf(choices)
  val selected: MutableState<Set<Answer>> = mutableStateOf(setOf())
  val showAnswer = mutableStateOf(false)

  val canUndoOrReset
    get() = selected.value.isNotEmpty() && !showAnswer.value

  val canValidate
    get() = selected.value.size == maxAnswers

  val codeSample: String
    get() =
      if (selected.value.isNotEmpty()) {
        // TODO: find a way to automatically replace the placeholder with the selected answers
        """println("${selected.value.first().text}")"""
      } else {
        initialCodeSample
      }

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
