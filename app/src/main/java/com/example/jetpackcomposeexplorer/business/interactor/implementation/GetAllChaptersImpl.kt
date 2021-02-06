package com.example.jetpackcomposeexplorer.business.interactor.implementation

import com.example.jetpackcomposeexplorer.business.course.data.kotlin.KOTLIN
import com.example.jetpackcomposeexplorer.business.domain.Chapter
import com.example.jetpackcomposeexplorer.business.domain.state.DataState
import com.example.jetpackcomposeexplorer.business.interactor.abstraction.GetAllChapters
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetAllChaptersImpl : GetAllChapters {
  override fun <Model> invoke(modelBuilder: (List<Chapter>) -> Model): Flow<DataState<Model>> =
      flow {
        emit(
            DataState.data(
                data = modelBuilder(KOTLIN)
            )
        )
      }
}
