package com.example.jetpackcomposeexplorer.framework.datasource.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface LessonDao {
  @Insert
  fun insert(lesson: LessonEntity)

  @Query("SELECT count(id) > 0 FROM lesson WHERE id = :id")
  fun exists(id: String): Boolean

  @Query("SELECT * FROM lesson")
  fun readAll(): List<LessonEntity>

  @Query("SELECT * FROM lesson where id = :id")
  fun read(id: String): LessonEntity

  @Update
  fun update(vararg lessons: LessonEntity)
}
