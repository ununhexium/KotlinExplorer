package net.lab0.jetpackcomposeexplorer.business.domain


data class LessonProgress(
    val lessonId: String,
    val successCount: Int,
    val failureCount: Int,
)
