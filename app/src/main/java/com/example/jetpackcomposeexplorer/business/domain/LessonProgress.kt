package com.example.jetpackcomposeexplorer.business.domain


data class LessonProgress(
    val lessonId: String,
    val successCount: Int,
    val failureCount: Int,
)
