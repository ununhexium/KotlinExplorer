package com.example.jetpackcomposeexplorer.business.interactor.implementation

import com.example.jetpackcomposeexplorer.business.domain.LessonProgress
import com.example.jetpackcomposeexplorer.business.domain.state.DataState
import com.example.jetpackcomposeexplorer.business.interactor.abstraction.SaveLessonProgress
import com.example.jetpackcomposeexplorer.business.persistence.abstraction.LessonProgressDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class SaveLessonProgressImpl(
    private val dataSource: LessonProgressDataSource,
) : SaveLessonProgress {
  override fun invoke(
      lessonProgress: LessonProgress,
  ): Flow<DataState<*>> = flow {
    dataSource.saveLessonProgress(lessonProgress)
    emit(DataState.data<Any>())
  }
}
