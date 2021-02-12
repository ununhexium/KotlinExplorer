package com.example.jetpackcomposeexplorer.business.interactor.implementation

import com.example.jetpackcomposeexplorer.business.domain.LessonProgress
import com.example.jetpackcomposeexplorer.business.domain.state.DataState
import com.example.jetpackcomposeexplorer.business.persistence.abstraction.LessonProgressDataSource
import com.example.jetpackcomposeexplorer.mvi.Resource
import com.google.common.truth.Truth.assertThat
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.fail
import java.util.concurrent.atomic.AtomicReference

internal class GetLessonsInProgressCountImplTest {
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

    val action = GetLessonsInProgressCountImpl(dao)

    // when
    action().collect {
      // then
      assertThat(it).isInstanceOf(Resource.LoadedResource::class.java)
      it as Resource.LoadedResource<Int>
      assertThat(it.resource).isEqualTo(inProgress.size)
    }
  }
}
