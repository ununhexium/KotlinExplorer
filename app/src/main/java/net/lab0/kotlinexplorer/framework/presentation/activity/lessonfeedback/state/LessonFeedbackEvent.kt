package net.lab0.kotlinexplorer.framework.presentation.activity.lessonfeedback.state

import net.lab0.kotlinexplorer.mvi.UiEvent

sealed class LessonFeedbackEvent : UiEvent {
  object Empty: LessonFeedbackEvent()
  object Submit: LessonFeedbackEvent()
}