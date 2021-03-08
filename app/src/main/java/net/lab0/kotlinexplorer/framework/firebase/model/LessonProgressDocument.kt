package net.lab0.kotlinexplorer.framework.firebase.model

data class LessonProgressDocument(
  val id: String,
  val lessonId: String,
  val successCount: Int,
  val failureCount: Int,
)
