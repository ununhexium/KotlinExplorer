package net.lab0.jetpackcomposeexplorer.business.persistence.implementation

import net.lab0.jetpackcomposeexplorer.business.domain.LessonProgress
import net.lab0.jetpackcomposeexplorer.business.persistence.abstraction.LessonProgressDataSource
import net.lab0.jetpackcomposeexplorer.framework.db.LessonProgressDao
import net.lab0.jetpackcomposeexplorer.framework.db.LessonProgressEntity
import net.lab0.jetpackcomposeexplorer.framework.util.FromEntity
import net.lab0.jetpackcomposeexplorer.framework.util.ToEntity

class LessonProgressDataSourceImpl(
    private val dao: LessonProgressDao,
    private val fromEntityMapper: FromEntity<LessonProgressEntity, LessonProgress>,
    private val toEntityMapper: ToEntity<LessonProgressEntity, LessonProgress>,
) : LessonProgressDataSource {
  override suspend fun getLessonProgress(id: String): LessonProgress {
    return fromEntityMapper(dao.getLesson(id))
  }

  override suspend fun saveLessonProgress(lessonProgress: LessonProgress): Long {
    return dao.insertLesson(toEntityMapper(lessonProgress))
  }

  override suspend fun getLessonsInProgress(): List<LessonProgress> {
    return dao.getLessons().map { fromEntityMapper(it) }
  }
}
