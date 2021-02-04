package com.example.jetpackcomposeexplorer.business.persistence.implementation

import com.example.jetpackcomposeexplorer.business.domain.LessonProgress
import com.example.jetpackcomposeexplorer.business.persistence.abstraction.LessonProgressDataSource
import com.example.jetpackcomposeexplorer.framework.db.LessonProgressDao

class LessonProgressDataSourceImpl(
    val dao: LessonProgressDao,
) : LessonProgressDataSource {
  override suspend fun getLessonProgress(id: String): LessonProgress {
    return dao.getLesson(id)
  }

  override suspend fun saveLessonProgress(lessonProgress: LessonProgress): Int {
    return dao.updateLesson(lessonProgress)
  }

  override suspend fun getLessonsInProgress(): List<LessonProgress> {
    TODO("Not yet implemented")
  }
}
