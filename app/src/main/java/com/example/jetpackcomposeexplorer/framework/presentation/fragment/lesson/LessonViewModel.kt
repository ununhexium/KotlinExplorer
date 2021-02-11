package com.example.jetpackcomposeexplorer.framework.presentation.fragment.lesson

import android.util.Log
import com.example.jetpackcomposeexplorer.business.course.data.kotlin.KOTLIN
import com.example.jetpackcomposeexplorer.business.domain.Lesson
import com.example.jetpackcomposeexplorer.business.domain.LessonProgress
import com.example.jetpackcomposeexplorer.business.interactor.abstraction.SaveLessonProgress
import com.example.jetpackcomposeexplorer.framework.presentation.fragment.lesson.state.CodeAnswerState
import com.example.jetpackcomposeexplorer.framework.presentation.fragment.lesson.state.LessonStateEvent
import com.example.jetpackcomposeexplorer.framework.presentation.fragment.lesson.state.LessonViewState
import com.example.jetpackcomposeexplorer.mvi.BaseViewModel
import com.example.jetpackcomposeexplorer.utils.Do

class LessonViewModel(
    private val saveLessonProgress: SaveLessonProgress
) : BaseViewModel<LessonStateEvent, LessonViewState>(
    LessonStateEvent.Empty,
    LessonViewState(Lesson.EMPTY, 0)
) {

  override suspend fun doJobForEvent(event: LessonStateEvent) {
    Do exhaustive when (event) {
      LessonStateEvent.Empty -> Unit
      is LessonStateEvent.GoToNextPage ->
        updateUi {
          when {
            it.page < it.lesson.pages.size -> it.copy(
                page = it.page + 1,
                answers = it.answers + event.correctness
            )
            else -> {
              // stop changing the page, we're already in the end
              Log.w(TAG, "getJobForEvent: tried to go further than the last page")
              it
            }
          }
        }
      is LessonStateEvent.LoadLesson -> KOTLIN
          .flatMap { it.lessons }
          .firstOrNull { it.id == event.lessonId }
          ?.let { lesson ->
            updateUi { state ->
              state.copy(lesson = lesson)
            }
          }
      LessonStateEvent.SaveLessonProgress ->
        processResource(
            saveLessonProgress(
                LessonProgress(
                    uiDataState.value.lesson.id,
                    uiDataState.value.answersCount(CodeAnswerState.SUCCESS),
                    uiDataState.value.answersCount(CodeAnswerState.FAILURE),
                )
            )
        ) { }
    }
  }

  fun nextPage(correctness: CodeAnswerState = CodeAnswerState.NEUTRAL) =
      emitFastEvent(LessonStateEvent.GoToNextPage(correctness))

  fun loadLesson(lessonId: String) =
      emitFastEvent(LessonStateEvent.LoadLesson(lessonId))

  fun saveLesson() =
      emitSlowEvent(LessonStateEvent.SaveLessonProgress)
}
