package net.lab0.kotlinexplorer.framework.presentation.activity.lesson.codequestion.mvi

import net.lab0.kotlinexplorer.mvi.UiEvent

sealed class LessonPageUiEvent: UiEvent {
  object Empty: LessonPageUiEvent()
  object Undo: LessonPageUiEvent()
  object Reset: LessonPageUiEvent()
  object Validate: LessonPageUiEvent()
  data class SelectAnswer(val answer:Int): LessonPageUiEvent()
}