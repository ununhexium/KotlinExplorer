package net.lab0.jetpackcomposeexplorer.framework.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_profile")
data class UserProfileEntity(
  @ColumnInfo(name = "alias")
  val alias: String,

  @PrimaryKey(autoGenerate = true)
  val id: Int = 0,
)