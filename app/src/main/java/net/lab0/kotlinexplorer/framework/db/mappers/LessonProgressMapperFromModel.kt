package net.lab0.kotlinexplorer.framework.db.mappers

import net.lab0.kotlinexplorer.business.domain.LessonProgress
import net.lab0.kotlinexplorer.framework.db.LessonProgressEntity
import net.lab0.kotlinexplorer.framework.util.FromModel

class LessonProgressMapperFromModel : FromModel<LessonProgressEntity, LessonProgress> {

  override fun invoke(domain: LessonProgress) =
      LessonProgressEntity(
          domain.lessonId,
          domain.successCount,
          domain.failureCount,
      )
}
