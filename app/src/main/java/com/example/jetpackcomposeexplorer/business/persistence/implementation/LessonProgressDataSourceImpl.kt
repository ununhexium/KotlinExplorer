package com.example.jetpackcomposeexplorer.business.persistence.implementation

import com.example.jetpackcomposeexplorer.business.domain.LessonProgress
import com.example.jetpackcomposeexplorer.business.persistence.abstraction.LessonProgressDataSource
import com.example.jetpackcomposeexplorer.framework.db.LessonProgressDao
import com.example.jetpackcomposeexplorer.framework.db.LessonProgressEntity
import com.example.jetpackcomposeexplorer.framework.util.FromEntity
import com.example.jetpackcomposeexplorer.framework.util.ToEntity

class LessonProgressDataSourceImpl(
    val dao: LessonProgressDao,
    val fromEntityMapper: FromEntity<LessonProgressEntity, LessonProgress>,
    val toEntityMapper: ToEntity<LessonProgressEntity, LessonProgress>,
) : LessonProgressDataSource {
  override suspend fun getLessonProgress(id: String): LessonProgress {
    return fromEntityMapper(dao.getLesson(id))
  }

  override suspend fun saveLessonProgress(lessonProgress: LessonProgress): Int {
    return dao.updateLesson(toEntityMapper(lessonProgress))
  }

  override suspend fun getLessonsInProgress(): List<LessonProgress> {
    TODO("Not yet implemented")
  }
}
