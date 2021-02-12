package net.lab0.jetpackcomposeexplorer.framework.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface LessonProgressDao {
  @Query("SELECT * FROM LessonProgress WHERE id = :id")
  suspend fun getLesson(id: String): LessonProgressEntity

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun insertLesson(lessonProgress: LessonProgressEntity): Long

  @Query("SELECT * FROM LessonProgress")
  suspend fun getLessons(): List<LessonProgressEntity>
}
