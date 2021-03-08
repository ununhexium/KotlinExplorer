package net.lab0.kotlinexplorer.business.domain


data class LessonProgress(
  val lessonId: String,
  val successCount: Int,
  val failureCount: Int,
)
