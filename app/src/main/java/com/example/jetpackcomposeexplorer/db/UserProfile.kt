package com.example.jetpackcomposeexplorer.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class UserProfile(
  @ColumnInfo(name = "alias")
  val alias: String,

  @PrimaryKey(autoGenerate = true)
  val id: Int = 0,
)
