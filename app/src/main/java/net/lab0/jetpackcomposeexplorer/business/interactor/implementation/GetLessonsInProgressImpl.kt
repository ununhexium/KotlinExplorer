package net.lab0.jetpackcomposeexplorer.business.interactor.implementation

import net.lab0.jetpackcomposeexplorer.business.domain.LessonProgress
import net.lab0.jetpackcomposeexplorer.business.interactor.abstraction.GetLessonsInProgress
import net.lab0.jetpackcomposeexplorer.business.persistence.abstraction.LessonProgressDataSource
import net.lab0.jetpackcomposeexplorer.mvi.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetLessonsInProgressImpl(
    private val dataSource: LessonProgressDataSource,
) : GetLessonsInProgress {
  override fun invoke(): Flow<Resource<List<LessonProgress>>> =
      flow {
        emit(
            Resource.LoadedResource(
                dataSource.getLessonsInProgress()
            )
        )
      }
}
