package com.example.jetpackcomposeexplorer.framework.datasource.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration

@Database(entities = [UserProfileEntity::class, LessonEntity::class], version = 2)
abstract class ExplorerDatabase : RoomDatabase() {
  companion object {
    lateinit var db: ExplorerDatabase
      private set

    fun init(applicationContext: Context) {
      db = Room
          .databaseBuilder(
              applicationContext,
              ExplorerDatabase::class.java,
              "jetpack-explorer"
          )
          .addMigrations(

          )
          .build()

      if (db.userDao().getAll().isEmpty()) {
        db.userDao().insert(UserProfileEntity("World"))
      }
    }
  }

  abstract fun userDao(): UserProfileDao
  abstract fun lessonDao(): LessonDao
}
