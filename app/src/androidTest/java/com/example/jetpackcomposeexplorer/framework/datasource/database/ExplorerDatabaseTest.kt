package com.example.jetpackcomposeexplorer.framework.datasource.database

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@Suppress("IllegalIdentifier")
@RunWith(AndroidJUnit4::class)
class ExplorerDatabaseTest {

  private lateinit var userProfileDao: UserProfileDao
  private lateinit var lessonDao: LessonDao
  private lateinit var db: ExplorerDatabase

  @Before
  fun before() {
    val context = ApplicationProvider.getApplicationContext<Context>()
    db = Room
        .inMemoryDatabaseBuilder(context, ExplorerDatabase::class.java)
        .build()
    userProfileDao = db.userDao()
    lessonDao = db.lessonDao()
  }

  @After
  @Throws(IOException::class)
  fun closeDb() {
    db.close()
  }

  @Test
  @Throws(Exception::class)
  fun writeUserAndReadInList() {
    val user = UserProfileEntity( "george", -1)

    userProfileDao.insert(user)
    val byName = userProfileDao.getAll().first()
    assertThat(byName.alias).isEqualTo("george")
  }

  @Test
  fun `can tell if a lesson doesnt exists`() {
    // given
    val id = "foo"

    // then
    assertThat(lessonDao.exists(id)).isEqualTo(false)
  }

  @Test
  fun `can tell if a lesson exists`() {
    // given
    val id = "foo"
    lessonDao.insert(LessonEntity(id))

    // then
    assertThat(lessonDao.exists(id)).isEqualTo(true)
  }
}
