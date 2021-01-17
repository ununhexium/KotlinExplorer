package com.example.jetpackcomposeexplorer.presentation.ui.codequestion

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.example.jetpackcomposeexplorer.findAndEdit
import com.example.jetpackcomposeexplorer.presentation.components.Answer
import com.example.jetpackcomposeexplorer.ui.frame.KotlinCode

class CodeQuestionViewModel(
    val question: String,
    val initialCodeSample: String,
    val maxAnswers: Int,
    choices: List<Answer>,
    val answerValidator: (List<Answer>) -> Boolean = { false },
) {
  constructor(
      question: String,
      codeSample: String,
      maxAnswers: Int,
      vararg choices: String,
      answerValidator: (List<Answer>) -> Boolean,
  ) : this(
      question,
      codeSample,
      maxAnswers,
      choices.mapIndexed { index, s -> Answer(index, s, false) },
      answerValidator
  )

  constructor(
      question: String,
      codeSample: String,
      maxAnswers: Int,
      vararg choices: Answer,
      answerValidator: (List<Answer>) -> Boolean,
  ) : this(
      question,
      codeSample,
      maxAnswers,
      choices.toList(),
      answerValidator
  )

  val answers: MutableState<List<Answer>> = mutableStateOf(choices)
  val selected: MutableState<Set<Answer>> = mutableStateOf(setOf())
  val canValidate: MutableState<Boolean> = mutableStateOf(false)

  val codeSample: String
    get() =
      if (selected.value.isNotEmpty()) {
        // TODO: find a way to automatically replace the placeholder with the selected answers
        """println("${selected.value.first().text}")"""
      } else {
        initialCodeSample
      }

  init {
    refreshValidate()
  }

  fun select(answer: Answer) {
    if (selected.value.size >= maxAnswers) return // can't select more

    answers.findAndEdit(answer) {
      it.copy(used = true)
    }
    selected.value = selected.value.toMutableSet().also {
      it.add(answer.copy(used = true))
    }
    refreshValidate()
  }

  fun undo() {
    val last = selected.value.last()
    selected.value = selected.value.toMutableSet().also { it.remove(last) }
    answers.findAndEdit(last) {
      it.copy(used = false)
    }
    refreshValidate()
  }

  fun reset() {
    selected.value = mutableSetOf()
    answers.value = answers.value.map { it.copy(used = false) }
    refreshValidate()
  }

  private fun refreshValidate() {
    canValidate.value = selected.value.size == maxAnswers
  }

  fun isCorrectAnswer(): Boolean {
    return answerValidator(selected.value.toList())
  }
}
