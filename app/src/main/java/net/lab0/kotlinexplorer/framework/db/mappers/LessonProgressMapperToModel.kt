package net.lab0.kotlinexplorer.framework.db.mappers

import net.lab0.kotlinexplorer.business.domain.LessonProgress
import net.lab0.kotlinexplorer.framework.db.LessonProgressEntity
import net.lab0.kotlinexplorer.framework.util.ToModel

class LessonProgressMapperToModel : ToModel<LessonProgressEntity, LessonProgress> {

  override fun invoke(entity: LessonProgressEntity) =
      LessonProgress(
          entity.id,
          entity.successCount,
          entity.failureCount,
      )
}
