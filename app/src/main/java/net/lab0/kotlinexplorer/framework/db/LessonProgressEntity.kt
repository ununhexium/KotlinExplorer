package net.lab0.kotlinexplorer.framework.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "LessonProgress")
data class LessonProgressEntity(
  @PrimaryKey
  val id: String,

  @ColumnInfo(name = "successCount")
  val successCount: Int,

  @ColumnInfo(name = "failureCount")
  val failureCount: Int,
)
