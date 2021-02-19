package net.lab0.kotlinexplorer.framework.firebase.implementation

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import net.lab0.kotlinexplorer.business.domain.feedback.Feedback
import net.lab0.kotlinexplorer.framework.firebase.abstraction.FeedbackService
import net.lab0.kotlinexplorer.framework.firebase.model.FeedbackDocument
import net.lab0.kotlinexplorer.framework.firebase.model.feedbackCollection
import net.lab0.kotlinexplorer.framework.util.FromDomain
import net.lab0.kotlinexplorer.framework.util.ToDomain

class FeedbackServiceImpl(
    private val firebaseAuth: FirebaseAuth,
    private val firestore: FirebaseFirestore,
    private val fromDomain: FromDomain<FeedbackDocument, Feedback>,
    private val toDomain: ToDomain<FeedbackDocument, Feedback>,
) : FeedbackService {

  override suspend fun insertOrUpdateFeedback(feedback: Feedback) {
    firestore
        .feedbackCollection(firebaseAuth.uid!!)
        .document(feedback.id.toString())
        .set(fromDomain(feedback))
        .await()
  }

  override suspend fun readAllUserFeedbacks(): List<Feedback> {
    return firestore
        .feedbackCollection(firebaseAuth.uid!!)
        .get()
        .await()
        .toObjects(FeedbackDocument::class.java)
        .map { toDomain(it) }
  }

}
