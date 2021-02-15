package net.lab0.kotlinexplorer.framework.db

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import org.junit.After
import org.junit.Before
import java.io.IOException

open class DatabaseTest {
  protected lateinit var db: ExplorerDatabase

  protected lateinit var userProfileDao: UserProfileDao
  protected lateinit var lessonProgressDao: LessonProgressDao

  @Before
  fun configureDatabase() {
    val context = ApplicationProvider.getApplicationContext<Context>()
    db = Room
        .inMemoryDatabaseBuilder(context, ExplorerDatabase::class.java)
        .build()

    userProfileDao = db.getUserProfileDao()
    lessonProgressDao = db.getLessonProgressDao()
  }

  @After
  @Throws(IOException::class)
  fun closeDatabase() {
    db.close()
  }
}