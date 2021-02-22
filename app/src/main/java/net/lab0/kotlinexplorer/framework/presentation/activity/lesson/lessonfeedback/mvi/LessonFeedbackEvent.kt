package net.lab0.kotlinexplorer.framework.presentation.activity.lesson.lessonfeedback.mvi

import net.lab0.kotlinexplorer.mvi.UiEvent

sealed class LessonFeedbackEvent : UiEvent {
  object Empty: LessonFeedbackEvent()
  object Submit: LessonFeedbackEvent()
}