package com.example.jetpackcomposeexplorer.presentation.ui.codequestion

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.example.jetpackcomposeexplorer.findAndEdit
import com.example.jetpackcomposeexplorer.presentation.components.Answer

class CodeQuestionViewModel {
  private var choicesSet = false
  private var maxAnswers = 0
  val answers: MutableState<List<Answer>> = mutableStateOf(listOf())
  val selected: MutableState<Set<Answer>> = mutableStateOf(setOf())
  val canValidate: MutableState<Boolean> = mutableStateOf(false)

  fun setChoices(maxAnswers: Int, choices: List<Answer>) {
    if (choicesSet) throw IllegalStateException("Can't only set the choices once.")

    val mutableAnswers = answers.value.toMutableList()
    mutableAnswers.addAll(choices.toList())
    answers.value = mutableAnswers
    this.maxAnswers = maxAnswers
    refreshValidate()

    this.choicesSet = true
  }

  fun setChoices(maxAnswers: Int, vararg choices: String) {
    setChoices(maxAnswers, choices.mapIndexed { index, s -> Answer(index, s, false) })
  }

  fun setChoices(maxAnswers: Int, vararg choices: Answer) {
    setChoices(maxAnswers, choices.toList())
  }

  fun select(answer: Answer) {
    if(selected.value.size >= maxAnswers) return // can't select more

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
}
