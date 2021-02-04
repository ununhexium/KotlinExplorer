package com.example.jetpackcomposeexplorer.business.interactor.implementation

import com.example.jetpackcomposeexplorer.business.domain.LessonProgress
import com.example.jetpackcomposeexplorer.business.interactor.abstraction.GetLessonsInProgress
import com.example.jetpackcomposeexplorer.business.persistence.abstraction.LessonProgressDataSource

class GetLessonsInProgressImpl(
    private val dao:LessonProgressDataSource
) : GetLessonsInProgress {
  override suspend fun invoke(): List<LessonProgress> {
    return dao.getLessonsInProgress()
  }
}