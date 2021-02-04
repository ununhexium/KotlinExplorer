package com.example.jetpackcomposeexplorer.business.persistence.abstraction

import com.example.jetpackcomposeexplorer.business.domain.LessonProgress

interface LessonProgressDataSource {

  suspend fun getLessonProgress(id: String): LessonProgress

  suspend fun saveLessonProgress(lessonProgress: LessonProgress): Int

  suspend fun getLessonsInProgress(): List<LessonProgress>
}
