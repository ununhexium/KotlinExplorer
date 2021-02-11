package com.example.jetpackcomposeexplorer.framework.presentation.lesson.state

import com.example.jetpackcomposeexplorer.business.domain.state.StateEvent

sealed class LessonStateEvent : StateEvent {
  object Empty : LessonStateEvent()
  data class LoadLesson(val lessonId: String) : LessonStateEvent()
  data class GoToNextPage(val correctness: CodeAnswerState) : LessonStateEvent()
  object SaveLessonProgress: LessonStateEvent()
}
