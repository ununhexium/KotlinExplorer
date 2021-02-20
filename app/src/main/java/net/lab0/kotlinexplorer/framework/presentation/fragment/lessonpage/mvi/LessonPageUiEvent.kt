package net.lab0.kotlinexplorer.framework.presentation.fragment.lessonpage.mvi

import net.lab0.kotlinexplorer.framework.presentation.fragment.lessonpage.Answer
import net.lab0.kotlinexplorer.mvi.UiEvent

sealed class LessonPageUiEvent: UiEvent {
  object Empty: LessonPageUiEvent()
  object Undo: LessonPageUiEvent()
  object Reset: LessonPageUiEvent()
  object Validate: LessonPageUiEvent()
  object Next: LessonPageUiEvent()
  data class SelectAnswer(val answer:Answer): LessonPageUiEvent()
}
