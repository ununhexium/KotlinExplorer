package com.example.jetpackcomposeexplorer.business.interactor.implementation

import com.example.jetpackcomposeexplorer.business.domain.LessonProgress
import com.example.jetpackcomposeexplorer.business.persistence.abstraction.LessonProgressDataSource
import com.google.common.truth.Truth.assertThat
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

internal class GetLessonsInProgressImplTest {
  companion object {
    val dao = mockk<LessonProgressDataSource>()
  }


  @BeforeEach
  fun beforeEach() {
    clearAllMocks()
  }

  @Test
  fun canGetTheLessonProgress_whenItAlreadyExists() = runBlocking {
    // given
    val inProgress = listOf(
        LessonProgress("id1", 1, 2),
        LessonProgress("id2", 2, 0),
    )
    coEvery {
      dao.getLessonsInProgress()
    } returns inProgress

    val action = GetLessonsInProgressImpl(dao)

    // when
    val result = action()

    // then
    assertThat(result).isEqualTo(inProgress)
  }
}