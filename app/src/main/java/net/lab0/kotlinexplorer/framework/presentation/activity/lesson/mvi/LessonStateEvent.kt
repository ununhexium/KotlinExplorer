package net.lab0.kotlinexplorer.framework.presentation.activity.lesson.mvi

import android.content.Context
import net.lab0.kotlinexplorer.business.domain.LessonPage
import net.lab0.kotlinexplorer.business.domain.problemreport.ProblemReport
import net.lab0.kotlinexplorer.business.domain.state.StateEvent
import net.lab0.kotlinexplorer.framework.presentation.activity.lesson.AnswerCorrectness

sealed class LessonStateEvent : StateEvent {
  object Empty : LessonStateEvent()
  data class CountMark(
    val currentPage: LessonPage,
    val correctness: AnswerCorrectness
  ) : LessonStateEvent()

  object SaveLessonProgress : LessonStateEvent()

  data class ReportProblem(
    val problemReport: ProblemReport,
    val context: Context
  ) : LessonStateEvent()

  data class ExtraLessonsRequest(
    val liking: String?,
    val whyMoreLessons: String?,
    val comment: String?,
    val thenNav: () -> Unit,
  ) : LessonStateEvent()
}
