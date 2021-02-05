package com.example.jetpackcomposeexplorer.business.interactor.abstraction

import com.example.jetpackcomposeexplorer.business.domain.LessonProgress
import com.example.jetpackcomposeexplorer.business.domain.state.DataState
import kotlinx.coroutines.flow.Flow

interface GetLessonsInProgress {
  operator fun <Model> invoke(modelBuilder: (Int) -> Model): Flow<DataState<Model>>
}

