package net.lab0.kotlinexplorer.business.domain.feedback

import java.util.*

data class Feedback(
    val durationRating: DurationRating,
    val difficultyRating: DifficultyRating,
    val timestamp: Date = Date(),
    val id: UUID = UUID.randomUUID(),
)
