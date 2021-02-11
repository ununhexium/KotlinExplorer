package com.example.jetpackcomposeexplorer.framework.presentation.lesson

import com.example.jetpackcomposeexplorer.business.course.data.kotlin.KOTLIN
import com.example.jetpackcomposeexplorer.business.domain.Lesson
import com.example.jetpackcomposeexplorer.framework.presentation.lesson.state.LessonStateEvent
import com.example.jetpackcomposeexplorer.framework.presentation.lesson.state.LessonViewState
import com.example.jetpackcomposeexplorer.mvi.BaseViewModel
import com.example.jetpackcomposeexplorer.utils.Do

class LessonViewModel : BaseViewModel<LessonStateEvent, LessonViewState>(
    LessonStateEvent.Empty,
    LessonViewState(Lesson.EMPTY, 0)
) {

  override suspend fun getJobForEvent(event: LessonStateEvent) {
    Do exhaustive when (event) {
      LessonStateEvent.GoToNextPage ->
        updateUi {
          when {
            it.page < it.lesson.pages.size -> it.copy(page = it.page + 1)
            else -> it // stop increasing the page count, we're already in the end
          }
        }
      LessonStateEvent.Empty -> Unit
      is LessonStateEvent.LoadLesson -> KOTLIN
          .flatMap { it.lessons }
          .firstOrNull { it.id == event.lessonId }
          ?.let { lesson ->
            updateUi { state ->
              state.copy(lesson = lesson)
            }
          }
    }
  }

  fun nextPage() = emitFastEvent(LessonStateEvent.GoToNextPage)
  fun loadLesson(lessonId: String) = emitFastEvent(LessonStateEvent.LoadLesson(lessonId))
}
