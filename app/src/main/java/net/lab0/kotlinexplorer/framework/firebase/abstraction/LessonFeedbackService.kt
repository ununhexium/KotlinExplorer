package net.lab0.kotlinexplorer.framework.firebase.abstraction

import net.lab0.kotlinexplorer.business.domain.feedback.LessonFeedback

interface LessonFeedbackService {
  suspend fun insertOrUpdateFeedback(lessonFeedback:LessonFeedback)
  suspend fun readAllUserFeedbacks(): List<LessonFeedback>
}
