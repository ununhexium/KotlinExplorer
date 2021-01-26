package com.example.jetpackcomposeexplorer.framework.presentation.ui.codequestion

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.example.jetpackcomposeexplorer.framework.CoroutineTestRule
import com.example.jetpackcomposeexplorer.framework.datasource.FakeGenerator
import com.example.jetpackcomposeexplorer.framework.datasource.database.ExplorerDatabase
import com.example.jetpackcomposeexplorer.framework.datasource.database.LessonDao
import com.example.jetpackcomposeexplorer.framework.datasource.database.LessonEntity
import com.example.jetpackcomposeexplorer.framework.datasource.mapper.LessonMapper
import com.example.jetpackcomposeexplorer.framework.datasource.service.LessonDaoService
import com.example.jetpackcomposeexplorer.framework.datasource.service.LessonDaoServiceImpl
import com.google.common.truth.Truth.assertThat
import io.mockk.clearAllMocks
import io.mockk.every
import io.mockk.spyk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.asExecutor
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.TestCoroutineScope
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.io.IOException

@ExperimentalCoroutinesApi
class QuizViewModelTest {

  @get:Rule
  var coroutinesTestRule = CoroutineTestRule()

  private val lessonFinder = FakeGenerator.fakeLessonFinder

  private lateinit var db: ExplorerDatabase
  private lateinit var lessonDao: LessonDao
  private lateinit var lessonDaoService: LessonDaoService


  private val testDispatcher = TestCoroutineDispatcher()
  private val testScope = TestCoroutineScope(testDispatcher)

  @Before
  fun before() {
    clearAllMocks()

    val context = ApplicationProvider.getApplicationContext<Context>()
    db = Room
        .inMemoryDatabaseBuilder(context, ExplorerDatabase::class.java)
        .setQueryExecutor(testDispatcher.asExecutor())
        .setTransactionExecutor(testDispatcher.asExecutor())
        .build()

    lessonDao = spyk(db.lessonDao())
    lessonDaoService = LessonDaoServiceImpl(
        lessonDao,
        LessonMapper(lessonFinder)
    )
  }

  @After
  fun closeDb() {
    db.close()
  }

  @Test
  fun canSaveTheLessonsStatus_whenReachingTheEndOfTheQuiz() = testScope.runBlockingTest {
    // given
    val lesson = FakeGenerator.generateFakeLesson(pagesCount = 2)
    val vm = QuizViewModel(lesson, lessonDaoService)

    // when go to the next page
    vm.goToNextPage()

    // then don't save the lesson now
    // TODO: save progress? Save quiz results so far?
    assertThat(lessonDao.exists(lesson.lessonData.id)).isFalse()

    // when go to the end (after the last lesson page)
    vm.goToNextPage()

    // TODO: fix test with synced coroutine

    // then save the lesson as done
    assertThat(lessonDao.exists(lesson.lessonData.id)).isTrue()
    val read = lessonDao.read(lesson.lessonData.id)
    assertThat(read).isEqualTo(LessonEntity(lesson.lessonData.id, true))
  }
}