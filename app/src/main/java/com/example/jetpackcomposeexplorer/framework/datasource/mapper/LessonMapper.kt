package com.example.jetpackcomposeexplorer.framework.datasource.mapper

import com.example.jetpackcomposeexplorer.business.data.course.abstraction.CourseRepository
import com.example.jetpackcomposeexplorer.business.domain.Exercise
import com.example.jetpackcomposeexplorer.business.util.EntityMapper
import com.example.jetpackcomposeexplorer.framework.datasource.database.LessonEntity
import javax.inject.Inject

class LessonMapper
@Inject
constructor(
    private val courseRepository: CourseRepository,
) : EntityMapper<LessonEntity, Exercise> {
  override fun fromEntity(entity: LessonEntity): Exercise {
    val lessonData = courseRepository.findLessonById(entity.id)
    return Exercise(
        lessonData,
        entity.completed,
    )
  }

  override fun toEntity(domainModel: Exercise): LessonEntity {
    return LessonEntity(
        domainModel.lessonData.id,
        domainModel.completed
    )
  }
}
