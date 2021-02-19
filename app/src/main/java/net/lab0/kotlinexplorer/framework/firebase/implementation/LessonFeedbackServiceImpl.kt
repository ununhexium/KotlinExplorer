package net.lab0.kotlinexplorer.framework.firebase.implementation

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import net.lab0.kotlinexplorer.business.domain.feedback.LessonFeedback
import net.lab0.kotlinexplorer.framework.firebase.abstraction.LessonFeedbackService
import net.lab0.kotlinexplorer.framework.firebase.model.FeedbackDocument
import net.lab0.kotlinexplorer.framework.firebase.model.feedbackCollection
import net.lab0.kotlinexplorer.framework.util.FromDomain
import net.lab0.kotlinexplorer.framework.util.ToDomain

class LessonFeedbackServiceImpl(
    private val firebaseAuth: FirebaseAuth,
    private val firestore: FirebaseFirestore,
    private val fromDomain: FromDomain<FeedbackDocument, LessonFeedback>,
    private val toDomain: ToDomain<FeedbackDocument, LessonFeedback>,
) : LessonFeedbackService {

  override suspend fun insertOrUpdateFeedback(lessonFeedback: LessonFeedback) {
    firestore
        // TODO: use WithUidMixin ?
        .feedbackCollection(firebaseAuth.uid!!)
        .document(lessonFeedback.id.toString())
        .set(fromDomain(lessonFeedback))
        .await()
  }

  override suspend fun readAllUserFeedbacks(): List<LessonFeedback> {
    return firestore
        .feedbackCollection(firebaseAuth.uid!!)
        .get()
        .await()
        .toObjects(FeedbackDocument::class.java)
        .map { toDomain(it) }
  }
}
