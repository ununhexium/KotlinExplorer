package com.example.jetpackcomposeexplorer.business.interactor.implementation

import com.example.jetpackcomposeexplorer.business.domain.LessonProgress
import com.example.jetpackcomposeexplorer.business.domain.state.DataState
import com.example.jetpackcomposeexplorer.business.interactor.abstraction.GetLessonsInProgress
import com.example.jetpackcomposeexplorer.framework.db.LessonProgressDao
import com.example.jetpackcomposeexplorer.framework.db.mappers.LessonProgressMapperFromEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetLessonsInProgressImpl(
    private val dao: LessonProgressDao,
    private val fromEntity: LessonProgressMapperFromEntity,
) : GetLessonsInProgress {
  override fun <Model> invoke(modelBuilder: (List<LessonProgress>) -> Model): Flow<DataState<Model>> =
      flow {
        emit(
            DataState.data(
                data = modelBuilder(
                    dao.getLessons().map {
                      this@GetLessonsInProgressImpl.fromEntity(it)
                    }
                )
            )
        )
      }
}
