package net.lab0.kotlinexplorer.framework.presentation.activity.lesson.lessonfeedback

import net.lab0.kotlinexplorer.business.domain.feedback.DifficultyRating
import net.lab0.kotlinexplorer.business.domain.feedback.DurationRating
import net.lab0.kotlinexplorer.business.domain.feedback.LessonFeedback
import net.lab0.kotlinexplorer.business.interactor.abstraction.ReloadLessonFeedback
import net.lab0.kotlinexplorer.business.interactor.abstraction.SendLessonFeedback
import net.lab0.kotlinexplorer.framework.presentation.activity.lesson.lessonfeedback.mvi.LessonFeedbackEvent
import net.lab0.kotlinexplorer.framework.presentation.activity.lesson.lessonfeedback.mvi.LessonFeedbackState
import net.lab0.kotlinexplorer.mvi.BaseViewModel
import net.lab0.kotlinexplorer.utils.Do

class LessonFeedbackViewModel(
  val sendLessonFeedback: SendLessonFeedback,
  val reloadLessonFeedback: ReloadLessonFeedback,
) : BaseViewModel<LessonFeedbackEvent, LessonFeedbackState>(
  LessonFeedbackEvent.Empty,
  LessonFeedbackState()
) {
  fun init(
    lessonId: String,
  ) {
    updateUi {
      it.copy(
        lessonId = lessonId,
        durationEvaluation = DurationRating.UNSET,
        difficultyEvaluation = DifficultyRating.UNSET,
      )
    }
    emitFastEvent(LessonFeedbackEvent.Init(lessonId))
  }

  override suspend fun doJobForEvent(event: LessonFeedbackEvent) {
    Do exhaustive when (event) {
      LessonFeedbackEvent.Empty -> Unit
      LessonFeedbackEvent.Submit -> {
        val state = uiDataState.value
        // TODO: update older feedback if it exists
        // TODO: show previous feedback if it exists
        if (
          state.existingDifficultyEvaluation != state.difficultyEvaluation ||
          state.existingDurationEvaluation != state.durationEvaluation
        ) {
          processResource(
            sendLessonFeedback(
              LessonFeedback(
                state.lessonId,
                state.durationEvaluation,
                state.difficultyEvaluation,
              )
            )
          ) {}
        }
        Unit
      }
      is LessonFeedbackEvent.Init ->
        processResource(
          reloadLessonFeedback(event.lessonId)
        ) { feedback ->
          feedback?.let {
            updateUi { state ->
              state.copy(
                durationEvaluation = feedback.durationRating,
                difficultyEvaluation = feedback.difficultyRating,
                existingDurationEvaluation = feedback.durationRating,
                existingDifficultyEvaluation = feedback.difficultyRating,
              )
            }
          }
        }
    }
  }

  fun validate() {
    emitSlowEvent(LessonFeedbackEvent.Submit)
  }

  fun selectDuration(durationEvaluation: DurationRating) {
    updateUi { ui ->
      ui.copy(durationEvaluation = durationEvaluation)
    }
  }

  fun selectDifficulty(difficultyEvaluation: DifficultyRating) {
    updateUi { ui ->
      ui.copy(difficultyEvaluation = difficultyEvaluation)
    }
  }

}
