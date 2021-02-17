package net.lab0.kotlinexplorer.business.persistence.implementation

import net.lab0.kotlinexplorer.business.domain.LessonProgress
import net.lab0.kotlinexplorer.business.persistence.abstraction.LessonProgressDataSource
import net.lab0.kotlinexplorer.framework.db.LessonProgressDao
import net.lab0.kotlinexplorer.framework.db.LessonProgressEntity
import net.lab0.kotlinexplorer.framework.util.ToDomain
import net.lab0.kotlinexplorer.framework.util.FromDomain

class LessonProgressDataSourceImpl(
    private val dao: LessonProgressDao,
    private val toDomainMapper: ToDomain<LessonProgressEntity, LessonProgress>,
    private val fromDomainMapper: FromDomain<LessonProgressEntity, LessonProgress>,
) : LessonProgressDataSource {
  override suspend fun getLessonProgress(id: String): LessonProgress {
    return toDomainMapper(dao.getLesson(id))
  }

  override suspend fun saveLessonProgress(lessonProgress: LessonProgress): Long {
    return dao.insertLesson(fromDomainMapper(lessonProgress))
  }

  override suspend fun getLessonsInProgress(): List<LessonProgress> {
    return dao.getLessons().map { toDomainMapper(it) }
  }
}
