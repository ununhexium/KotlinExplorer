package com.example.jetpackcomposeexplorer.framework.datasource.mapper

import com.example.jetpackcomposeexplorer.business.course.data.kotlin.LessonFinder
import com.example.jetpackcomposeexplorer.business.domain.Lesson
import com.example.jetpackcomposeexplorer.business.util.EntityMapper
import com.example.jetpackcomposeexplorer.framework.datasource.database.LessonEntity

class LessonMapper(
    private val lessonFinder: LessonFinder,
) : EntityMapper<LessonEntity, Lesson> {
  override fun fromEntity(entity: LessonEntity): Lesson {
    val lessonData = lessonFinder.findLessonById(entity.id)
    return Lesson(
        lessonData,
        entity.completed,
    )
  }

  override fun toEntity(domainModel: Lesson): LessonEntity {
    return LessonEntity(
        domainModel.lessonData.id,
        domainModel.completed
    )
  }
}
