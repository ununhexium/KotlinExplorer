package net.lab0.kotlinexplorer.framework.firebase.implementation

import com.google.common.truth.Truth.assertThat
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.runBlocking
import net.lab0.kotlinexplorer.business.domain.feedback.Feedback
import net.lab0.kotlinexplorer.business.domain.feedback.DifficultyRating
import net.lab0.kotlinexplorer.business.domain.feedback.DurationRating
import net.lab0.kotlinexplorer.framework.firebase.abstraction.FeedbackService
import net.lab0.kotlinexplorer.framework.firebase.model.FeedbackDocument
import net.lab0.kotlinexplorer.framework.util.FromDomain
import net.lab0.kotlinexplorer.framework.util.ToDomain
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject

@FlowPreview
@ExperimentalCoroutinesApi
@HiltAndroidTest
internal class FeedbackServiceImplTest {
  private lateinit var feedbackService: FeedbackService

  @get:Rule(order = 0)
  var hiltRule = HiltAndroidRule(this)

  @Inject
  lateinit var firebaseAuth: FirebaseAuth

  @Inject
  lateinit var firestore: FirebaseFirestore

  @Inject
  lateinit var fromDomain: FromDomain<FeedbackDocument, Feedback>

  @Inject
  lateinit var toDomain: ToDomain<FeedbackDocument, Feedback>

  @Before
  fun before() {
    hiltRule.inject()
    feedbackService = FeedbackServiceImpl(
        firebaseAuth,
        firestore,
        fromDomain,
        toDomain,
    )
  }

  @Test
  fun createReadFeedback():Unit = runBlocking {
    // given
    val feedback = Feedback(
        DurationRating.BALANCED,
        DifficultyRating.BALANCED,
    )
    
    // when
    feedbackService.insertOrUpdateFeedback(feedback)
    val allFeedbacks = feedbackService.readAllUserFeedbacks()

    // then
    assertThat(allFeedbacks).containsAtLeastElementsIn(listOf(feedback))
  }

}
