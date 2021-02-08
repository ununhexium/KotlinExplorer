package com.example.jetpackcomposeexplorer.business.interactor.abstraction

import com.example.jetpackcomposeexplorer.business.domain.LessonProgress
import com.example.jetpackcomposeexplorer.business.domain.state.DataState
import kotlinx.coroutines.flow.Flow

interface SaveLessonProgress {
  operator fun invoke(
      lessonProgress: LessonProgress,
  ): Flow<DataState<*>>
}
