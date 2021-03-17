package net.lab0.kotlinexplorer.business.interactor.implementation

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import net.lab0.kotlinexplorer.business.domain.LessonProgress
import net.lab0.kotlinexplorer.business.interactor.abstraction.GetLessonsInProgress
import net.lab0.kotlinexplorer.business.persistence.abstraction.LessonProgressDataSource
import net.lab0.kotlinexplorer.mvi.Resource

class GetLessonsInProgressImpl(
  private val dataSource: LessonProgressDataSource,
) : GetLessonsInProgress {
  override fun invoke(): Flow<Resource<List<LessonProgress>>> =
    flow {
      val resource = dataSource.getLessonsInProgress()
      emit(
        Resource.LoadedResource(resource)
      )
    }
}
