package com.example.jetpackcomposeexplorer.framework.db.mappers

import com.example.jetpackcomposeexplorer.business.domain.LessonProgress
import com.example.jetpackcomposeexplorer.framework.db.LessonProgressEntity
import com.example.jetpackcomposeexplorer.framework.util.FromEntity

class LessonProgressMapperFromEntity : FromEntity<LessonProgressEntity, LessonProgress> {

  override fun invoke(entity: LessonProgressEntity) =
      LessonProgress(
          entity.id,
          entity.successCount,
          entity.failureCount,
      )
}
