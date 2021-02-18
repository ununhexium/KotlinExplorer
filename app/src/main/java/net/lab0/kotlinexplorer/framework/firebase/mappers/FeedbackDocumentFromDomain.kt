package net.lab0.kotlinexplorer.framework.firebase.mappers

import com.google.firebase.Timestamp
import net.lab0.kotlinexplorer.business.domain.feedback.Feedback
import net.lab0.kotlinexplorer.framework.firebase.model.FeedbackDocument
import net.lab0.kotlinexplorer.framework.util.FromDomain

class FeedbackDocumentFromDomain : FromDomain<FeedbackDocument, Feedback> {
  override fun invoke(domain: Feedback) =
      FeedbackDocument(
          domain.id.toString(),
          Timestamp(domain.timestamp),
          domain.durationRating.code,
          domain.difficultyRating.code,
      )
}
