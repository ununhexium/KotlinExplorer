package net.lab0.jetpackcomposeexplorer.business.persistence.abstraction

import net.lab0.jetpackcomposeexplorer.business.domain.LessonProgress

interface LessonProgressDataSource {

  suspend fun getLessonProgress(id: String): LessonProgress

  suspend fun saveLessonProgress(lessonProgress: LessonProgress): Long

  suspend fun getLessonsInProgress(): List<LessonProgress>
}
