package com.example.jetpackcomposeexplorer.framework.datasource.service

import com.example.jetpackcomposeexplorer.business.domain.Lesson
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

  override suspend fun getOrCreateLesson(id: String): Lesson {
    if (!lessonDao.exists(id)) {
      lessonDao.insert(LessonEntity(id, false))
    }

    return lessonMapper.fromEntity(lessonDao.read(id))
  }

  override suspend fun markAsDone(lesson: Lesson): Lesson {
    if (!lesson.completed) {
      if (!lessonDao.exists(lesson.lessonData.id)) {
        lessonDao.insert(lessonMapper.toEntity(lesson.copy(completed = true)))
      } else {
        lessonDao.update(lessonMapper.toEntity(lesson.copy(completed = true)))
      }
    }

    return lessonMapper.fromEntity(
        lessonDao.read(lesson.lessonData.id)
    )
  }
}
