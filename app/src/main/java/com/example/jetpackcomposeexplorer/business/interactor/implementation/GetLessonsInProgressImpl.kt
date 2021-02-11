package com.example.jetpackcomposeexplorer.business.interactor.implementation

import com.example.jetpackcomposeexplorer.business.domain.LessonProgress
import com.example.jetpackcomposeexplorer.business.interactor.abstraction.GetLessonsInProgress
import com.example.jetpackcomposeexplorer.business.persistence.abstraction.LessonProgressDataSource
import com.example.jetpackcomposeexplorer.mvi.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetLessonsInProgressImpl(
    private val dataSource: LessonProgressDataSource,
) : GetLessonsInProgress {
  override fun invoke(): Flow<Resource<List<LessonProgress>>> =
      flow {
        emit(
            Resource.LoadedResource(
                dataSource.getLessonsInProgress()
            )
        )
      }
}
