package net.lab0.kotlinexplorer.framework.presentation.fragment.lesson

import android.util.Log
import net.lab0.kotlinexplorer.business.course.data.kotlin.KOTLIN
import net.lab0.kotlinexplorer.business.domain.Lesson
import net.lab0.kotlinexplorer.business.domain.LessonProgress
import net.lab0.kotlinexplorer.business.interactor.abstraction.SaveLessonProgress
import net.lab0.kotlinexplorer.framework.presentation.fragment.lesson.state.CodeAnswerState
import net.lab0.kotlinexplorer.framework.presentation.fragment.lesson.state.LessonStateEvent
import net.lab0.kotlinexplorer.framework.presentation.fragment.lesson.state.LessonViewState
import net.lab0.kotlinexplorer.mvi.BaseViewModel
import net.lab0.kotlinexplorer.utils.Do

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
              // TODO: save lesson when reaching the last page
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
