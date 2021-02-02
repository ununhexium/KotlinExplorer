package com.example.jetpackcomposeexplorer.framework.datasource.service

import com.example.jetpackcomposeexplorer.business.domain.Exercise
import com.example.jetpackcomposeexplorer.framework.datasource.database.LessonDao
import com.example.jetpackcomposeexplorer.framework.datasource.database.LessonEntity
import com.example.jetpackcomposeexplorer.framework.datasource.mapper.LessonMapper
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LessonDaoServiceImpl
@Inject
constructor(
    private val lessonDao: LessonDao,
    private val lessonMapper: LessonMapper,
) : LessonDaoService {

  override suspend fun getOrCreateLesson(id: String): Exercise {
    if (!lessonDao.exists(id)) {
      lessonDao.insert(LessonEntity(id, false))
    }

    return lessonMapper.fromEntity(lessonDao.read(id))
  }

  override suspend fun markAsDone(exercise: Exercise): Exercise {
    if (!exercise.completed) {
      if (!lessonDao.exists(exercise.lessonData.id)) {
        lessonDao.insert(lessonMapper.toEntity(exercise.copy(completed = true)))
      } else {
        lessonDao.update(lessonMapper.toEntity(exercise.copy(completed = true)))
      }
    }

    return lessonMapper.fromEntity(
        lessonDao.read(exercise.lessonData.id)
    )
  }
}
