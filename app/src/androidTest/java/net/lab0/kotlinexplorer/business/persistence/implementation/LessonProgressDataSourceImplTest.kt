package net.lab0.kotlinexplorer.business.persistence.implementation

import net.lab0.kotlinexplorer.business.domain.LessonProgress
import net.lab0.kotlinexplorer.framework.db.DatabaseTest
import net.lab0.kotlinexplorer.framework.db.mappers.LessonProgressMapperToModel
import net.lab0.kotlinexplorer.framework.db.mappers.LessonProgressMapperFromModel
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
        LessonProgressMapperToModel(),
        LessonProgressMapperFromModel(),
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
