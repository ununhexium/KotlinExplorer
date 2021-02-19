package net.lab0.kotlinexplorer.framework.firebase.model

import com.google.firebase.Timestamp
import net.lab0.kotlinexplorer.business.domain.feedback.DifficultyRating
import net.lab0.kotlinexplorer.business.domain.feedback.DurationRating

data class FeedbackDocument(
    val id: String = "",
    val createdAt: Timestamp = Timestamp(0, 0),
    val lessonId:String = "",
    val durationRating: String = DurationRating.UNSET.code,
    val difficultyRating: String = DifficultyRating.UNSET.code,
)
