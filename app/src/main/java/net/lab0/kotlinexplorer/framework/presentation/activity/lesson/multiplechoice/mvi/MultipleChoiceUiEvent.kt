package net.lab0.kotlinexplorer.framework.presentation.activity.lesson.multiplechoice.mvi

import net.lab0.kotlinexplorer.mvi.UiEvent

sealed class MultipleChoiceUiEvent: UiEvent {
  object Empty: MultipleChoiceUiEvent()
  object Validate: MultipleChoiceUiEvent()
  data class ToggleAnswer(val answer:Int): MultipleChoiceUiEvent()
}