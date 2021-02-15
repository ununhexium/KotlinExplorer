package net.lab0.kotlinexplorer.business.interactor.implementation

import net.lab0.kotlinexplorer.business.interactor.abstraction.GetLessonsInProgressCount
import net.lab0.kotlinexplorer.business.persistence.abstraction.LessonProgressDataSource
import net.lab0.kotlinexplorer.mvi.Resource
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
