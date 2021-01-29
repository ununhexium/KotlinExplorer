package com.example.jetpackcomposeexplorer.framework.datasource.service

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.example.jetpackcomposeexplorer.business.course.Finder
import com.example.jetpackcomposeexplorer.framework.datasource.FakeGenerator
import com.example.jetpackcomposeexplorer.framework.datasource.database.ExplorerDatabase
import com.example.jetpackcomposeexplorer.framework.datasource.database.LessonDao
import com.example.jetpackcomposeexplorer.framework.datasource.database.LessonEntity
import com.example.jetpackcomposeexplorer.framework.datasource.mapper.LessonMapper
import com.google.common.truth.Truth.assertThat
import io.mockk.clearAllMocks
import io.mockk.every
import io.mockk.mockk
import io.mockk.spyk
import io.mockk.verify
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import java.io.IOException

class LessonDaoServiceImplTest {
  private val lessonFinder: Finder = mockk()

  private lateinit var db: ExplorerDatabase
  private lateinit var lessonDao: LessonDao
  private lateinit var lessonDaoService: LessonDaoService

  @Before
  fun before() {
    clearAllMocks()

    val context = ApplicationProvider.getApplicationContext<Context>()
    db = Room
        .inMemoryDatabaseBuilder(context, ExplorerDatabase::class.java)
        .build()

    every { lessonFinder.findLessonById(any()) } answers {
      FakeGenerator.generateFakeLessonData(firstArg())
    }

    lessonDao = spyk(db.lessonDao())
    lessonDaoService = LessonDaoServiceImpl(
        lessonDao,
        LessonMapper(lessonFinder)
    )
  }

  @After
  @Throws(IOException::class)
  fun closeDb() {
    db.close()
  }

  @Test
  fun canGetALesson_WhenItDoesntAlreadyExistInTheDatabase() = runBlocking {
    // given
    val id = "116"

    // when
    val lesson = lessonDaoService.getOrCreateLesson(id)

    // then
    assertThat(lesson.lessonData.id).isEqualTo(id)
    verify(exactly = 1) {
      lessonDao.insert(LessonEntity(id, false))
    }
  }

  @Test
  fun canGetALesson_WhenItAlreadyExistsInTheDatabase() = runBlocking {
    // given
    val id = "116"
    // not using the mock so this creation doesn't count
    db.lessonDao().insert(LessonEntity(id, false))

    // when
    val lesson = lessonDaoService.getOrCreateLesson(id)

    // then
    assertThat(lesson.lessonData.id).isEqualTo(id)
    verify(exactly = 0) {
      lessonDao.insert(any())
    }
  }

  @Test
  fun canMarkALessonAsDone() = runBlocking {
    // given
    val id = "116"
    val lesson = lessonDaoService.getOrCreateLesson(id)

    // when
    val doneLesson = lessonDaoService.markAsDone(lesson)

    // then
    assertThat(doneLesson).isEqualTo(FakeGenerator.generateFakeLessonData(id))
    verify(exactly = 1) {
      lessonDao.update(LessonEntity(id, true))
    }
  }
}
