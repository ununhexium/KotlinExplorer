package net.lab0.kotlinexplorer.framework.firebase.abstraction

import net.lab0.kotlinexplorer.business.domain.feedback.Feedback

interface FeedbackService {
  suspend fun insertOrUpdateFeedback(feedback:Feedback)
  suspend fun readFeedbacks(): List<Feedback>
}
