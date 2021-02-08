package com.example.jetpackcomposeexplorer.framework.db.mappers

import com.example.jetpackcomposeexplorer.business.domain.LessonProgress
import com.example.jetpackcomposeexplorer.framework.db.LessonProgressEntity
import com.example.jetpackcomposeexplorer.framework.util.ToEntity

class LessonProgressMapperToEntity : ToEntity<LessonProgressEntity, LessonProgress> {

  override fun invoke(domain: LessonProgress) =
      LessonProgressEntity(
          domain.lessonId,
          domain.successCount,
          domain.failureCount,
      )
}
