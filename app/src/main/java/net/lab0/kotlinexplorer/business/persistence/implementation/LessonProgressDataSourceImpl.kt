package net.lab0.kotlinexplorer.business.persistence.implementation

import net.lab0.kotlinexplorer.business.domain.LessonProgress
import net.lab0.kotlinexplorer.business.persistence.abstraction.LessonProgressDataSource
import net.lab0.kotlinexplorer.framework.db.LessonProgressDao
import net.lab0.kotlinexplorer.framework.db.LessonProgressEntity
import net.lab0.kotlinexplorer.framework.util.ToModel
import net.lab0.kotlinexplorer.framework.util.FromModel

class LessonProgressDataSourceImpl(
    private val dao: LessonProgressDao,
    private val toModelMapper: ToModel<LessonProgressEntity, LessonProgress>,
    private val fromModelMapper: FromModel<LessonProgressEntity, LessonProgress>,
) : LessonProgressDataSource {
  override suspend fun getLessonProgress(id: String): LessonProgress {
    return toModelMapper(dao.getLesson(id))
  }

  override suspend fun saveLessonProgress(lessonProgress: LessonProgress): Long {
    return dao.insertLesson(fromModelMapper(lessonProgress))
  }

  override suspend fun getLessonsInProgress(): List<LessonProgress> {
    return dao.getLessons().map { toModelMapper(it) }
  }
}
