package net.lab0.kotlinexplorer.framework.firebase

data class LessonProgressDocument(
    val lessonId: String,
    val successCount: Int,
    val failureCount: Int,
)
