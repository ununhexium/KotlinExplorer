package net.lab0.jetpackcomposeexplorer.business.persistence.implementation

import net.lab0.jetpackcomposeexplorer.business.domain.LessonProgress
import net.lab0.jetpackcomposeexplorer.framework.db.DatabaseTest
import net.lab0.jetpackcomposeexplorer.framework.db.mappers.LessonProgressMapperFromEntity
import net.lab0.jetpackcomposeexplorer.framework.db.mappers.LessonProgressMapperToEntity
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
