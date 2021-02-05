package com.example.jetpackcomposeexplorer.framework.db

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Update

@Dao
interface LessonProgressDao {
  @Query("SELECT * FROM LessonProgress WHERE id = :id")
  suspend fun getLesson(id: String): LessonProgressEntity

  @Update
  suspend fun updateLesson(lessonProgress: LessonProgressEntity): Int
}
