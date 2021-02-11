package com.example.jetpackcomposeexplorer.business.interactor.implementation

import com.example.jetpackcomposeexplorer.business.domain.state.DataState
import com.example.jetpackcomposeexplorer.business.interactor.abstraction.GetLessonsInProgressCount
import com.example.jetpackcomposeexplorer.business.persistence.abstraction.LessonProgressDataSource
import com.example.jetpackcomposeexplorer.mvi.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetLessonsInProgressCountImpl(
    private val dao: LessonProgressDataSource,
) : GetLessonsInProgressCount {
  override fun invoke(): Flow<Resource<Int>> = flow {
    emit(
        Resource.LoadedResource(
            dao.getLessonsInProgress().size
        )
    )
  }
}
