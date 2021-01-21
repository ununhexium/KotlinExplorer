package com.example.jetpackcomposeexplorer.framework.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [UserProfile::class], version = 1)
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
          .build()

      if (db.getUserDao().getAll().isEmpty()) {
        db.getUserDao().insert(UserProfile("World"))
      }
    }
  }

  abstract fun getUserDao(): UserProfileDao
}
