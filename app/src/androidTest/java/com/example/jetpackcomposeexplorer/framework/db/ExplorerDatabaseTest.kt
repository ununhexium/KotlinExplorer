package com.example.jetpackcomposeexplorer.framework.db

import android.content.Context
import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class ExplorerDatabaseTest: DatabaseTest() {
  @Test
  @Throws(Exception::class)
  fun writeUserAndReadInList() {
    val user = UserProfileEntity( "george", -1)

    userProfileDao.insert(user)
    val byName = userProfileDao.getAll().first()
    assertThat(byName.alias).isEqualTo("george")
  }
}
