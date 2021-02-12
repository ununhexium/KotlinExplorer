package net.lab0.jetpackcomposeexplorer.business.interactor.implementation

import net.lab0.jetpackcomposeexplorer.business.interactor.abstraction.GetLessonsInProgressCount
import net.lab0.jetpackcomposeexplorer.business.persistence.abstraction.LessonProgressDataSource
import net.lab0.jetpackcomposeexplorer.mvi.Resource
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
