package net.lab0.kotlinexplorer.framework.presentation.activity.lesson.codequestion.mvi

import net.lab0.kotlinexplorer.mvi.UiEvent

sealed class CodeQuestionUiEvent: UiEvent {
  object Empty: CodeQuestionUiEvent()
  object Undo: CodeQuestionUiEvent()
  object Reset: CodeQuestionUiEvent()
  object Validate: CodeQuestionUiEvent()
  data class SelectAnswer(val answer:Int): CodeQuestionUiEvent()
}
