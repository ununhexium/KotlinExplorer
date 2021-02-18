package net.lab0.kotlinexplorer.business.domain.feedback

import java.util.*

data class Feedback(
    val durationRating: LessonDuration,
    val difficultyRating: LessonDifficulty,
    val timestamp: Date = Date(),
    val id: UUID = UUID.randomUUID(),
)
