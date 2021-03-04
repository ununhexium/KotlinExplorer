package net.lab0.kotlinexplorer.framework.presentation.activity.lesson.lessonfeedback

import net.lab0.kotlinexplorer.business.domain.feedback.DifficultyRating
import net.lab0.kotlinexplorer.business.domain.feedback.DurationRating
import net.lab0.kotlinexplorer.business.domain.feedback.LessonFeedback
import net.lab0.kotlinexplorer.business.interactor.abstraction.ReloadLessonFeedback
import net.lab0.kotlinexplorer.business.interactor.abstraction.SendLessonFeedback
import net.lab0.kotlinexplorer.framework.presentation.activity.lesson.lessonfeedback.mvi.LessonFeedbackEvent
import net.lab0.kotlinexplorer.framework.presentation.activity.lesson.lessonfeedback.mvi.LessonFeedbackState
import net.lab0.kotlinexplorer.framework.presentation.composable.feedback.EvaluationTopic
import net.lab0.kotlinexplorer.mvi.BaseViewModel
import net.lab0.kotlinexplorer.utils.Do
import net.lab0.kotlinexplorer.utils.printLogD

class LessonFeedbackViewModel(
    val sendLessonFeedback: SendLessonFeedback,
    val reloadLessonFeedback: ReloadLessonFeedback,
) : BaseViewModel<LessonFeedbackEvent, LessonFeedbackState>(
    LessonFeedbackEvent.Empty,
    LessonFeedbackState()
) {
  fun init(
      lessonId: String,
      durationEvaluation: EvaluationTopic<DurationRating> = EvaluationTopic.empty(),
      difficultyEvaluation: EvaluationTopic<DifficultyRating> = EvaluationTopic.empty(),
  ) {
    updateUi {
      it.copy(
          lessonId = lessonId,
          durationEvaluation = durationEvaluation,
          difficultyEvaluation = difficultyEvaluation
      )
    }
  }

  override suspend fun doJobForEvent(event: LessonFeedbackEvent) {
    Do exhaustive when (event) {
      LessonFeedbackEvent.Empty -> Unit
      LessonFeedbackEvent.Submit -> {
        val state = uiDataState.value
        // TODO: update older feedback if it exists
        // TODO: show previous feedback if it exists
        printLogD(TAG, "Feedback: sending...")
        processResource(
          sendLessonFeedback(
            LessonFeedback(
              state.lessonId,
              state.durationEvaluation.options[state.durationIndex],
              state.difficultyEvaluation.options[state.difficultyIndex],
            )
          )
        ) {}
        printLogD(TAG, "Feedback: processing...")
      }
    }
  }

  fun validate() {
    emitSlowEvent(LessonFeedbackEvent.Submit)
  }

  fun selectDuration(index: Int?) {
    index?.let {
      updateUi { ui ->
        ui.copy(durationIndex = index)
      }
    }
  }

  fun selectDifficulty(index: Int?) {
    index?.let {
      updateUi { ui ->
        ui.copy(difficultyIndex = index)
      }
    }
  }
}
