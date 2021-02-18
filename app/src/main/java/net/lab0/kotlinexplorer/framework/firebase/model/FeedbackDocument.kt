package net.lab0.kotlinexplorer.framework.firebase.model

import com.google.firebase.Timestamp
import net.lab0.kotlinexplorer.business.domain.feedback.LessonDifficulty
import net.lab0.kotlinexplorer.business.domain.feedback.LessonDuration

data class FeedbackDocument(
    val id: String = "",
    val createdAt: Timestamp = Timestamp.now(),
    val durationRating: String = LessonDuration.UNSET.code,
    val difficultyRating: String = LessonDifficulty.UNSET.code,
)
