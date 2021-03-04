package net.lab0.kotlinexplorer.framework.firebase.implementation

import com.google.android.gms.tasks.Tasks
import com.google.common.truth.Truth.assertThat
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.tasks.await
import net.lab0.kotlinexplorer.business.domain.feedback.DifficultyRating
import net.lab0.kotlinexplorer.business.domain.feedback.DurationRating
import net.lab0.kotlinexplorer.business.domain.feedback.LessonFeedback
import net.lab0.kotlinexplorer.framework.firebase.abstraction.LessonFeedbackService
import net.lab0.kotlinexplorer.framework.firebase.model.LessonFeedbackDocument
import net.lab0.kotlinexplorer.framework.firebase.model.feedbackCollection
import net.lab0.kotlinexplorer.framework.util.FromDomain
import net.lab0.kotlinexplorer.framework.util.ToDomain
import net.lab0.kotlinexplorer.injection.FirestoreInstanceModule
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject

@FlowPreview
@ExperimentalCoroutinesApi
@UninstallModules(FirestoreInstanceModule::class)
@HiltAndroidTest
internal class LessonFeedbackServiceImplTest {
  private lateinit var lessonFeedbackService: LessonFeedbackService

  @get:Rule(order = 0)
  var hiltRule = HiltAndroidRule(this)

  @Inject
  lateinit var firebaseAuth: FirebaseAuth

  @Inject
  lateinit var firestore: FirebaseFirestore

  @Inject
  lateinit var fromDomain: FromDomain<LessonFeedbackDocument, LessonFeedback>

  @Inject
  lateinit var toDomain: ToDomain<LessonFeedbackDocument, LessonFeedback>

  @Before
  fun before() {
    hiltRule.inject()

    Tasks.await(firebaseAuth.signInAnonymously())

    lessonFeedbackService = LessonFeedbackServiceImpl(
      firestore,
      fromDomain,
      toDomain,
    )
  }


  @Test
  fun createReadFeedback(): Unit = runBlocking {
    // given
    val lessonId = "lessonId"

    val feedback = LessonFeedback(
      lessonId,
      DurationRating.BALANCED,
      DifficultyRating.BALANCED,
    )

    // when
    lessonFeedbackService.insertOrUpdateFeedback(firebaseAuth.uid!!, feedback)

    // then
    val saved = toDomain(
      firestore.feedbackCollection(firebaseAuth.uid!!).document(lessonId).get().await()
        .toObject(LessonFeedbackDocument::class.java)!!
    )
    assertThat(saved).isEqualTo(feedback)
  }

  @Test
  fun canReadFeedbackForASpecificLesson() = runBlocking {
    // given
    val lessonId = "lessonId"

    val feedback = LessonFeedback(
      lessonId,
      DurationRating.BALANCED,
      DifficultyRating.BALANCED,
    )
    lessonFeedbackService.insertOrUpdateFeedback(firebaseAuth.uid!!, feedback)

    // when
    val previousFeedback =
      lessonFeedbackService.readUserFeedbackForLesson(firebaseAuth.uid!!, lessonId)

    // then
    assertThat(previousFeedback).isEqualTo(feedback)
  }
}
