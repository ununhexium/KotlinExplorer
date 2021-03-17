package net.lab0.kotlinexplorer.framework.presentation.activity.lesson.mvi

import android.content.Context
import android.widget.Toast
import dagger.hilt.android.lifecycle.HiltViewModel
import net.lab0.kotlinexplorer.business.domain.Lesson
import net.lab0.kotlinexplorer.business.domain.LessonPage
import net.lab0.kotlinexplorer.business.domain.LessonProgress
import net.lab0.kotlinexplorer.business.domain.problemreport.ProblemReport
import net.lab0.kotlinexplorer.business.interactor.abstraction.RequestExtraLessons
import net.lab0.kotlinexplorer.business.interactor.abstraction.SaveLessonProgress
import net.lab0.kotlinexplorer.business.interactor.abstraction.SendProblemReport
import net.lab0.kotlinexplorer.framework.presentation.activity.lesson.AnswerCorrectness
import net.lab0.kotlinexplorer.framework.presentation.activity.lesson.LessonViewState
import net.lab0.kotlinexplorer.mvi.BaseViewModel
import net.lab0.kotlinexplorer.utils.Do
import net.lab0.kotlinexplorer.utils.printLogD
import javax.inject.Inject

@HiltViewModel
class LessonViewModel @Inject constructor(
  private val saveLessonProgress: SaveLessonProgress,
  private val sendProblemReport: SendProblemReport,
  private val requestExtraLessons: RequestExtraLessons,
) : BaseViewModel<LessonStateEvent, LessonViewState>(
  LessonStateEvent.Empty,
  LessonViewState(Lesson.EMPTY)
) {

  override suspend fun doJobForEvent(event: LessonStateEvent) {
    Do exhaustive when (event) {

      LessonStateEvent.Empty -> {
      }

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
        updateUi { it ->
          it.copy(
            answers = it.answers + mapOf(event.currentPage to event.correctness)
          ).also {
            printLogD(LessonViewModel::class, "Count mark: $it")
          }
        }

      is LessonStateEvent.ReportProblem ->
        processResource(
          sendProblemReport(event.problemReport)
        ) {
          Toast.makeText(event.context, "Report sent", Toast.LENGTH_SHORT).show()
        }

      is LessonStateEvent.ExtraLessonsRequest ->
        processResource(
          requestExtraLessons(event.liking, event.whyMoreLessons, event.comment)
        ) {
          event.thenNav()
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

  fun request(
    liking: String?,
    whyMoreLessons: String?,
    comment: String?,
    // TODO: remove that shitty hack. If navigating too early, the model/coroutine doesn't have time ot submit the result
    thenNav: () -> Unit
  ) {
    emitSlowEvent(
      LessonStateEvent.ExtraLessonsRequest(
        liking,
        whyMoreLessons,
        comment,
        thenNav
      )
    )
  }
}
