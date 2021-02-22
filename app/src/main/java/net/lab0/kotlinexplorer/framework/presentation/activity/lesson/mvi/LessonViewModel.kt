package net.lab0.kotlinexplorer.framework.presentation.activity.lesson.mvi

import net.lab0.kotlinexplorer.business.domain.Lesson
import net.lab0.kotlinexplorer.business.domain.LessonPage
import net.lab0.kotlinexplorer.business.domain.LessonProgress
import net.lab0.kotlinexplorer.business.interactor.abstraction.SaveLessonProgress
import net.lab0.kotlinexplorer.framework.presentation.activity.lesson.CodeAnswerState
import net.lab0.kotlinexplorer.framework.presentation.activity.lesson.LessonViewState
import net.lab0.kotlinexplorer.mvi.BaseViewModel
import net.lab0.kotlinexplorer.utils.Do

class LessonViewModel(
    private val saveLessonProgress: SaveLessonProgress
) : BaseViewModel<LessonStateEvent, LessonViewState>(
    LessonStateEvent.Empty,
    LessonViewState(Lesson.EMPTY)
) {

  override suspend fun doJobForEvent(event: LessonStateEvent) {
    Do exhaustive when (event) {

      LessonStateEvent.Empty -> Unit

      LessonStateEvent.SaveLessonProgress ->
        processResource(
            saveLessonProgress(
                LessonProgress(
                    uiDataState.value.lesson.id,
                    uiDataState.value.answersCount(CodeAnswerState.SUCCESS),
                    uiDataState.value.answersCount(CodeAnswerState.FAILURE),
                )
            )
        ) {}

      is LessonStateEvent.CountMark ->
        updateUi {
          it.copy(
              answers = it.answers + mapOf(event.currentPage to event.correctness)
          )
        }
    }
  }

  fun saveLesson() =
      emitSlowEvent(LessonStateEvent.SaveLessonProgress)

  fun countMark(lessonPage: LessonPage, correctness: CodeAnswerState) =
      emitFastEvent(LessonStateEvent.CountMark(lessonPage, correctness))

  fun init(lesson:Lesson) {
    updateUi {
      it.copy(lesson = lesson)
    }
  }
}
