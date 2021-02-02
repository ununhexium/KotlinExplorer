package com.example.jetpackcomposeexplorer.framework.datasource.service

import com.example.jetpackcomposeexplorer.business.domain.Exercise

interface LessonDaoService {
  /**
   * Get or create (if it doesn't already exist) a lesson.
   */
  suspend fun getOrCreateLesson(id: String): Exercise

  /**
   * Mark the given lesson as done and returns the updated lesson
   */
  suspend fun markAsDone(exercise: Exercise): Exercise
}