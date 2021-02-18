package net.lab0.kotlinexplorer.framework.firebase.mappers

import com.google.firebase.Timestamp
import net.lab0.kotlinexplorer.business.domain.feedback.Feedback
import net.lab0.kotlinexplorer.business.domain.feedback.LessonDifficulty
import net.lab0.kotlinexplorer.business.domain.feedback.LessonDuration
import net.lab0.kotlinexplorer.framework.firebase.model.FeedbackDocument
import net.lab0.kotlinexplorer.framework.util.FromDomain
import net.lab0.kotlinexplorer.framework.util.ToDomain
import java.util.*

class FeedbackDocumentToDomain : ToDomain<FeedbackDocument, Feedback> {
  override fun invoke(entity: FeedbackDocument) =
      Feedback(
          LessonDuration.values().first { it.code == entity.durationRating },
          LessonDifficulty.values().first { it.code == entity.difficultyRating },
          entity.createdAt.toDate(),
          UUID.fromString(entity.id)
      )
}
