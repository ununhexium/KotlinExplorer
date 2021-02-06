package com.example.jetpackcomposeexplorer.business.interactor.abstraction

import com.example.jetpackcomposeexplorer.business.domain.Chapter
import com.example.jetpackcomposeexplorer.business.domain.LessonProgress
import com.example.jetpackcomposeexplorer.business.domain.state.DataState
import kotlinx.coroutines.flow.Flow

interface GetAllChapters {
  operator fun <Model> invoke(modelBuilder: (List<Chapter>) -> Model): Flow<DataState<Model>>
}

