package net.lab0.kotlinexplorer.framework.presentation.activity.lesson.mvi

import net.lab0.kotlinexplorer.business.domain.LessonPage
import net.lab0.kotlinexplorer.business.domain.state.StateEvent
import net.lab0.kotlinexplorer.framework.presentation.activity.lesson.CodeAnswerState

sealed class LessonStateEvent : StateEvent {
  object Empty : LessonStateEvent()
  data class CountMark(
      val currentPage: LessonPage,
      val correctness: CodeAnswerState
  ) : LessonStateEvent()

  object SaveLessonProgress : LessonStateEvent()
}
