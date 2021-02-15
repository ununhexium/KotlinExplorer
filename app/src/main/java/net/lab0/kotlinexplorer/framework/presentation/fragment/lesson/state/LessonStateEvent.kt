package net.lab0.kotlinexplorer.framework.presentation.fragment.lesson.state

import net.lab0.kotlinexplorer.business.domain.state.StateEvent

sealed class LessonStateEvent : StateEvent {
  object Empty : LessonStateEvent()
  data class LoadLesson(val lessonId: String) : LessonStateEvent()
  data class GoToNextPage(val correctness: CodeAnswerState) : LessonStateEvent()
  object SaveLessonProgress: LessonStateEvent()
}
