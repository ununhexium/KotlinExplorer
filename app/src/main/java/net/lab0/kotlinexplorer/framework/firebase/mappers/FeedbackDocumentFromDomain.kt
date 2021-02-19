package net.lab0.kotlinexplorer.framework.firebase.mappers

import com.google.firebase.Timestamp
import net.lab0.kotlinexplorer.business.domain.feedback.LessonFeedback
import net.lab0.kotlinexplorer.framework.firebase.model.FeedbackDocument
import net.lab0.kotlinexplorer.framework.util.FromDomain

class FeedbackDocumentFromDomain : FromDomain<FeedbackDocument, LessonFeedback> {
  override fun invoke(domain: LessonFeedback) =
      FeedbackDocument(
          id = domain.id.toString(),
          createdAt = Timestamp(domain.timestamp),
          lessonId = domain.lessonId,
          durationRating = domain.durationRating.code,
          difficultyRating = domain.difficultyRating.code,
      )
}
