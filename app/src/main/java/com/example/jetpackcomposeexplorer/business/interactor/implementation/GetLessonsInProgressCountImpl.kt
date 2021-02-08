package com.example.jetpackcomposeexplorer.business.interactor.implementation

import com.example.jetpackcomposeexplorer.business.domain.state.DataState
import com.example.jetpackcomposeexplorer.business.interactor.abstraction.GetLessonsInProgressCount
import com.example.jetpackcomposeexplorer.business.persistence.abstraction.LessonProgressDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetLessonsInProgressCountImpl(
    private val dao: LessonProgressDataSource,
) : GetLessonsInProgressCount {
  override fun <T> invoke(modelBuilder: (Int) -> T): Flow<DataState<T>> = flow {
    emit(
        DataState.data(
            data = modelBuilder(dao.getLessonsInProgress().size)
        )
    )
  }
}
