package net.lab0.kotlinexplorer.framework.presentation.activity.lesson.lessonfeedback.mvi

import net.lab0.kotlinexplorer.business.domain.feedback.DifficultyRating
import net.lab0.kotlinexplorer.business.domain.feedback.DurationRating
import net.lab0.kotlinexplorer.mvi.UiState

data class LessonFeedbackState(
  val lessonId: String,
  val durationEvaluation: DurationRating,
  val difficultyEvaluation: DifficultyRating,
  val existingDurationEvaluation: DurationRating?,
  val existingDifficultyEvaluation: DifficultyRating?,
) : UiState {
  constructor() : this(
    "",
    DurationRating.UNSET,
    DifficultyRating.UNSET,
    null,
    null,
  )
}
