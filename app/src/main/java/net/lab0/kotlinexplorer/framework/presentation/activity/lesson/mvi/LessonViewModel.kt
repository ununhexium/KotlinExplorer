package net.lab0.kotlinexplorer.framework.presentation.activity.lesson.mvi

import android.content.Context
import android.widget.Toast
import net.lab0.kotlinexplorer.business.domain.Lesson
import net.lab0.kotlinexplorer.business.domain.LessonPage
import net.lab0.kotlinexplorer.business.domain.LessonProgress
import net.lab0.kotlinexplorer.business.domain.problemreport.ProblemReport
import net.lab0.kotlinexplorer.business.interactor.abstraction.SaveLessonProgress
import net.lab0.kotlinexplorer.business.interactor.abstraction.SendProblemReport
import net.lab0.kotlinexplorer.framework.presentation.activity.lesson.AnswerCorrectness
import net.lab0.kotlinexplorer.framework.presentation.activity.lesson.LessonViewState
import net.lab0.kotlinexplorer.mvi.BaseViewModel
import net.lab0.kotlinexplorer.utils.Do

class LessonViewModel(
  private val saveLessonProgress: SaveLessonProgress,
  private val sendProblemReport: SendProblemReport,
) : BaseViewModel<LessonStateEvent, LessonViewState>(
  LessonStateEvent.Empty,
  LessonViewState(Lesson.EMPTY)
) {

  override suspend fun doJobForEvent(event: LessonStateEvent) {
    Do exhaustive when (event) {

      LessonStateEvent.Empty -> {}

      LessonStateEvent.SaveLessonProgress ->
        processResource(
          saveLessonProgress(
            LessonProgress(
              uiDataState.value.lesson.id,
              uiDataState.value.answersCount(AnswerCorrectness.SUCCESS),
              uiDataState.value.answersCount(AnswerCorrectness.FAILURE),
            )
          )
        ) {}

      is LessonStateEvent.CountMark ->
        updateUi {
          it.copy(
            answers = it.answers + mapOf(event.currentPage to event.correctness)
          )
        }

      is LessonStateEvent.ReportProblem ->
        processResource(
          sendProblemReport(event.problemReport)
        ) {
          Toast.makeText(event.context, "Report sent", Toast.LENGTH_SHORT).show()
        }
    }
  }

  fun saveLesson() =
    emitSlowEvent(LessonStateEvent.SaveLessonProgress)

  fun countMark(lessonPage: LessonPage, correctness: AnswerCorrectness) =
    emitFastEvent(LessonStateEvent.CountMark(lessonPage, correctness))

  fun init(lesson: Lesson) {
    updateUi {
      it.copy(lesson = lesson, answers = mapOf())
    }
  }

  fun onProblemReport(problemReport: ProblemReport, context: Context) {
    emitFastEvent(LessonStateEvent.ReportProblem(problemReport, context))
  }
}
