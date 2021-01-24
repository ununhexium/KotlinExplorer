package com.example.jetpackcomposeexplorer.framework.datasource.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "lesson")
data class LessonEntity(
    @PrimaryKey
    val id: String,

    @ColumnInfo(name = "done")
    val completed: Boolean = false,
)
