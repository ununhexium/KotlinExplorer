package net.lab0.jetpackcomposeexplorer.framework.db.mappers

import net.lab0.jetpackcomposeexplorer.business.domain.LessonProgress
import net.lab0.jetpackcomposeexplorer.framework.db.LessonProgressEntity
import net.lab0.jetpackcomposeexplorer.framework.util.ToEntity

class LessonProgressMapperToEntity : ToEntity<LessonProgressEntity, LessonProgress> {

  override fun invoke(domain: LessonProgress) =
      LessonProgressEntity(
          domain.lessonId,
          domain.successCount,
          domain.failureCount,
      )
}
