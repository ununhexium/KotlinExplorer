package net.lab0.jetpackcomposeexplorer.framework.db.mappers

import net.lab0.jetpackcomposeexplorer.business.domain.LessonProgress
import net.lab0.jetpackcomposeexplorer.framework.db.LessonProgressEntity
import net.lab0.jetpackcomposeexplorer.framework.util.FromEntity

class LessonProgressMapperFromEntity : FromEntity<LessonProgressEntity, LessonProgress> {

  override fun invoke(entity: LessonProgressEntity) =
      LessonProgress(
          entity.id,
          entity.successCount,
          entity.failureCount,
      )
}
