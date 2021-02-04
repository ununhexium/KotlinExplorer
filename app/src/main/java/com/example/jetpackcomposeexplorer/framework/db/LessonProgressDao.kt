package com.example.jetpackcomposeexplorer.framework.db

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Update
import com.example.jetpackcomposeexplorer.business.domain.LessonProgress

@Dao
interface LessonProgressDao {
  @Query("SELECT * FROM LessonProgress WHERE id = :id")
  suspend fun getLesson(id: String): LessonProgress

  @Update
  suspend fun updateLesson(lessonProgress: LessonProgress): Int
}
