package net.lab0.kotlinexplorer.framework.presentation.activity.lessonfeedback.mvi

import net.lab0.kotlinexplorer.business.domain.feedback.DifficultyRating
import net.lab0.kotlinexplorer.business.domain.feedback.DurationRating
import net.lab0.kotlinexplorer.framework.presentation.composable.feedback.EvaluationTopic
import net.lab0.kotlinexplorer.mvi.UiState

data class LessonFeedbackState(
    val lessonId: String = "",
    val durationEvaluation: EvaluationTopic<DurationRating> = EvaluationTopic.empty(),
    val difficultyEvaluation: EvaluationTopic<DifficultyRating> = EvaluationTopic.empty(),
    val durationIndex: Int = 0,
    val difficultyIndex: Int = 0,
) : UiState
