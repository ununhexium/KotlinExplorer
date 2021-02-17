package net.lab0.kotlinexplorer.business.interactor.abstraction

import net.lab0.kotlinexplorer.business.domain.LessonProgress
import net.lab0.kotlinexplorer.business.interactor.implementation.SaveLessonProgressImpl
import net.lab0.kotlinexplorer.business.persistence.implementation.LessonProgressDataSourceImpl
import net.lab0.kotlinexplorer.framework.db.DatabaseTest
import net.lab0.kotlinexplorer.framework.db.mappers.LessonProgressMapperToDomain
import net.lab0.kotlinexplorer.framework.db.mappers.LessonProgressMapperFromDomain
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test

internal class SaveLessonProgressImplTest : DatabaseTest() {

  @Before
  fun before() {
    super.configureDatabase()
  }

  @After
  fun after() {
    super.closeDatabase()
  }

  @Test
  fun canSaveTheLessonProgress() = runBlocking {
    // given
    val lessonProgress = LessonProgress(
        "lessonId",
        116,
        117,
    )

    val dataSource = LessonProgressDataSourceImpl(
        lessonProgressDao,
        LessonProgressMapperToDomain(),
        LessonProgressMapperFromDomain(),
    )

    val saveLessonProgress = SaveLessonProgressImpl(dataSource)

    // when
    saveLessonProgress(lessonProgress).collect {
      // then we don't expect any returned value
    }

    // and then
    assertThat(dataSource.getLessonsInProgress()).isEqualTo(listOf(lessonProgress))
  }
}
