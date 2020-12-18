package com.example.jetpackcomposeexplorer.db

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

@RunWith(AndroidJUnit4::class)
class ExplorerDatabaseTest {

  private lateinit var userProfileDao: UserProfileDao
  private lateinit var db: ExplorerDatabase

  @Before
  fun before() {
    val context = ApplicationProvider.getApplicationContext<Context>()
    db = Room
        .inMemoryDatabaseBuilder(context, ExplorerDatabase::class.java)
        .build()
    userProfileDao = db.getUserDao()
  }

  @After
  @Throws(IOException::class)
  fun closeDb() {
    db.close()
  }

  @Test
  @Throws(Exception::class)
  fun writeUserAndReadInList() {
    val user = UserProfile( "george", -1)

    userProfileDao.insert(user)
    val byName = userProfileDao.getAll().first()
    assertThat(byName.alias).isEqualTo("george")
  }
}
