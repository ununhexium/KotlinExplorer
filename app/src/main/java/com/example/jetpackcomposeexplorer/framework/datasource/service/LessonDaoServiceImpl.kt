package com.example.jetpackcomposeexplorer.framework.datasource.service

import com.example.jetpackcomposeexplorer.business.domain.Lesson
import com.example.jetpackcomposeexplorer.framework.datasource.database.LessonDao
import com.example.jetpackcomposeexplorer.framework.datasource.database.LessonEntity
import com.example.jetpackcomposeexplorer.framework.datasource.mapper.LessonMapper

class LessonDaoServiceImpl constructor(
    private val lessonDao: LessonDao,
    private val lessonMapper: LessonMapper,
) : LessonDaoService {
  override suspend fun getOrCreateLesson(id: String): Lesson {
    if (!lessonDao.exists(id)) {
      lessonDao.insert(LessonEntity(id, false))
    }

    return lessonMapper.fromEntity(lessonDao.read(id))
  }

  override suspend fun markAsDone(lesson: Lesson) {

  }
}
