package com.example.jetpackcomposeexplorer.framework.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(version = 3, entities = [UserProfileEntity::class, LessonProgressEntity::class])
abstract class ExplorerDatabase : RoomDatabase() {
  companion object {
    lateinit var db: ExplorerDatabase
      private set

    const val DATABASE_NAME: String = "jetpack-explorer"

    fun init(applicationContext: Context) {
      db = Room
          .databaseBuilder(
              applicationContext,
              ExplorerDatabase::class.java,
              DATABASE_NAME
          )
          .build()

      if (db.getUserProfileDao().getAll().isEmpty()) {
        db.getUserProfileDao().insert(UserProfileEntity("World"))
      }
    }
  }

  abstract fun getUserProfileDao(): UserProfileDao
  abstract fun getLessonProgressDao(): LessonProgressDao
}
