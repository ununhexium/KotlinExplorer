package net.lab0.kotlinexplorer.framework.firebase.implementation

import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import net.lab0.kotlinexplorer.business.domain.feedback.LessonFeedback
import net.lab0.kotlinexplorer.framework.firebase.abstraction.LessonFeedbackService
import net.lab0.kotlinexplorer.framework.firebase.model.LessonFeedbackDocument
import net.lab0.kotlinexplorer.framework.firebase.model.feedbackCollection
import net.lab0.kotlinexplorer.framework.util.FromDomain
import net.lab0.kotlinexplorer.framework.util.ToDomain

class LessonFeedbackServiceImpl(
  private val firestore: FirebaseFirestore,
  private val fromDomain: FromDomain<LessonFeedbackDocument, LessonFeedback>,
  private val toDomain: ToDomain<LessonFeedbackDocument, LessonFeedback>,
) : LessonFeedbackService {

  override suspend fun insertOrUpdateFeedback(uid: String, lessonFeedback: LessonFeedback) {
    firestore
      .feedbackCollection(uid)
      .document(lessonFeedback.lessonId)
      .also {
        print(it.path)
      }
      .set(fromDomain(lessonFeedback))
      .await()
  }

  override suspend fun readAllUserFeedbacks(uid: String): List<LessonFeedback> {
    return firestore
      .feedbackCollection(uid)
      .get()
      .await()
      .toObjects(LessonFeedbackDocument::class.java)
      .map { toDomain(it) }
  }

  override suspend fun readUserFeedbackForLesson(uid: String, lessonId: String): LessonFeedback? {
    return firestore
      .feedbackCollection(uid)
      .document(lessonId)
      .get()
      .await()
      .toObject(LessonFeedbackDocument::class.java)
      ?.let { toDomain(it) }
  }
}
