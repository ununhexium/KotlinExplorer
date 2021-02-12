package net.lab0.jetpackcomposeexplorer.business.interactor.implementation

import net.lab0.jetpackcomposeexplorer.business.course.data.kotlin.KOTLIN
import net.lab0.jetpackcomposeexplorer.business.domain.Chapter
import net.lab0.jetpackcomposeexplorer.business.interactor.abstraction.GetAllChapters
import net.lab0.jetpackcomposeexplorer.mvi.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetAllChaptersImpl : GetAllChapters {
  override fun invoke(): Flow<Resource<List<Chapter>>> =
    flow {
      emit(
          Resource.LoadedResource(
              KOTLIN
          )
      )
    }
}
