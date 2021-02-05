package com.example.jetpackcomposeexplorer.business.interactor.implementation

import com.example.jetpackcomposeexplorer.business.domain.LessonProgress
import com.example.jetpackcomposeexplorer.business.domain.state.DataState
import com.example.jetpackcomposeexplorer.business.interactor.abstraction.GetLessonsInProgress
import com.example.jetpackcomposeexplorer.business.persistence.abstraction.LessonProgressDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetLessonsInProgressImpl(
    private val dao: LessonProgressDataSource,
) : GetLessonsInProgress {
  override fun <T> invoke(modelBuilder: (Int) -> T): Flow<DataState<T>> = flow {
    dao.getLessonsInProgress()
  }
}
