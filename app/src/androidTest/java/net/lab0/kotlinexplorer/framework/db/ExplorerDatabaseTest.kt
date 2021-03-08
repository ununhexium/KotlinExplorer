package net.lab0.kotlinexplorer.framework.db

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth.assertThat
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ExplorerDatabaseTest : DatabaseTest() {
  @Test
  @Throws(Exception::class)
  fun writeUserAndReadInList() {
    val user = UserProfileEntity("george", -1)

    userProfileDao.insert(user)
    val byName = userProfileDao.getAll().first()
    assertThat(byName.alias).isEqualTo("george")
  }
}
