package net.lab0.kotlinexplorer.framework.firebase.mappers

import net.lab0.kotlinexplorer.business.domain.feedback.Feedback
import net.lab0.kotlinexplorer.business.domain.feedback.DifficultyRating
import net.lab0.kotlinexplorer.business.domain.feedback.DurationRating
import net.lab0.kotlinexplorer.framework.firebase.model.FeedbackDocument
import net.lab0.kotlinexplorer.framework.util.ToDomain
import java.util.*

class FeedbackDocumentToDomain : ToDomain<FeedbackDocument, Feedback> {
  override fun invoke(entity: FeedbackDocument) =
      Feedback(
          DurationRating.values().first { it.code == entity.durationRating },
          DifficultyRating.values().first { it.code == entity.difficultyRating },
          entity.createdAt.toDate(),
          UUID.fromString(entity.id)
      )
}
