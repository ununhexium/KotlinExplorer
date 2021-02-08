package com.example.jetpackcomposeexplorer.business.persistence.implementation

import com.example.jetpackcomposeexplorer.business.domain.LessonProgress
import com.example.jetpackcomposeexplorer.framework.db.DatabaseTest
import com.example.jetpackcomposeexplorer.framework.db.mappers.LessonProgressMapperFromEntity
import com.example.jetpackcomposeexplorer.framework.db.mappers.LessonProgressMapperToEntity
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test

internal class LessonProgressDataSourceImplTest : DatabaseTest() {
  lateinit var dataSource: LessonProgressDataSourceImpl

  @Before
  fun beforeEach() {
    super.configureDatabase()
    dataSource = LessonProgressDataSourceImpl(
        lessonProgressDao,
        LessonProgressMapperFromEntity(),
        LessonProgressMapperToEntity(),
    )
  }

  @After
  fun after() {
    super.closeDatabase()
  }

  @Test
  fun canSaveTheProgressForALesson() = runBlocking {
    // given
    val lessonProgress = LessonProgress(
        "someId",
        116,
        117
    )

    // when
    dataSource.saveLessonProgress(lessonProgress)

    // then
    assertThat(dataSource.getLessonsInProgress()).isEqualTo(listOf(lessonProgress))
  }
}
