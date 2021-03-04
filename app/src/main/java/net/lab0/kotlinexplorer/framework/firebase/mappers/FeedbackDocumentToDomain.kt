package net.lab0.kotlinexplorer.framework.firebase.mappers

import net.lab0.kotlinexplorer.business.domain.feedback.DifficultyRating
import net.lab0.kotlinexplorer.business.domain.feedback.DurationRating
import net.lab0.kotlinexplorer.business.domain.feedback.LessonFeedback
import net.lab0.kotlinexplorer.framework.firebase.model.LessonFeedbackDocument
import net.lab0.kotlinexplorer.framework.util.ToDomain
import java.util.*

class FeedbackDocumentToDomain : ToDomain<LessonFeedbackDocument, LessonFeedback> {
  override fun invoke(entity: LessonFeedbackDocument) =
      LessonFeedback(
          lessonId = entity.lessonId,
          durationRating = DurationRating.values().first { it.code == entity.durationRating },
          difficultyRating = DifficultyRating.values().first { it.code == entity.difficultyRating },
          timestamp = entity.createdAt.toDate(),
          id = UUID.fromString(entity.id)
      )
}
