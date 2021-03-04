package net.lab0.kotlinexplorer.business.interactor.implementation

import com.google.firebase.auth.FirebaseAuth
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import net.lab0.kotlinexplorer.business.domain.feedback.DifficultyRating
import net.lab0.kotlinexplorer.business.domain.feedback.DurationRating
import net.lab0.kotlinexplorer.business.domain.feedback.LessonFeedback
import net.lab0.kotlinexplorer.framework.firebase.abstraction.LessonFeedbackService
import org.junit.After
import org.junit.Before
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

@ExperimentalCoroutinesApi
internal class SendLessonFeedbackImplTest {
  private val testDispatcher = TestCoroutineDispatcher()

  private val uid = "testUser"
  private val auth: FirebaseAuth = mockk()

  @BeforeEach
  fun setup() {
    Dispatchers.setMain(testDispatcher)
    clearAllMocks()
    every { auth.uid } returns uid
  }

  @AfterEach
  fun tearDown() {
    Dispatchers.resetMain()
    testDispatcher.cleanupTestCoroutines()
  }

  @Test
  fun `when there is no feedback, don't send it to firebase`() = runBlockingTest {
    // given
    val lfs = mockk<LessonFeedbackService>()
    coEvery { lfs.insertOrUpdateFeedback(uid, any()) } returns Unit
    val f = SendLessonFeedbackImpl(lfs, auth)

    // when
    val lessonFeedback = LessonFeedback(
      "lessonId",
      DurationRating.UNSET,
      DifficultyRating.UNSET
    )
    f(lessonFeedback).collect()

    // then
    coVerify(exactly = 0) { lfs.insertOrUpdateFeedback(uid, any()) }
  }

  @Test
  fun `when there is some feedback, send it to firebase once`() = runBlockingTest {
    // given
    val lfs = mockk<LessonFeedbackService>()
    coEvery { lfs.insertOrUpdateFeedback(uid, any()) } returns Unit
    val f = SendLessonFeedbackImpl(lfs, auth)

    // when
    val lessonFeedback = LessonFeedback(
      "lessonId",
      DurationRating.BALANCED,
      DifficultyRating.BALANCED
    )
    f(lessonFeedback).collect()

    // then
    coVerify(exactly = 1) { lfs.insertOrUpdateFeedback(uid, lessonFeedback) }
  }
}