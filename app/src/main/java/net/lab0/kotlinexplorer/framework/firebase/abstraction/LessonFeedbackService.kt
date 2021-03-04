package net.lab0.kotlinexplorer.framework.firebase.abstraction

import net.lab0.kotlinexplorer.business.domain.feedback.LessonFeedback

interface LessonFeedbackService {
  suspend fun insertOrUpdateFeedback(uid:String, lessonFeedback:LessonFeedback)
  suspend fun readAllUserFeedbacks(uid:String): List<LessonFeedback>
  suspend fun readUserFeedbackForLesson(uid:String, lessonId:String): LessonFeedback?
}
