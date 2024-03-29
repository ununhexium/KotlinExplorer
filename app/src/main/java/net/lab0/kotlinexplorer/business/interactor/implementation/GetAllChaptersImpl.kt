package net.lab0.kotlinexplorer.business.interactor.implementation

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import net.lab0.kotlinexplorer.business.course.data.kotlin.KOTLIN
import net.lab0.kotlinexplorer.business.domain.Chapter
import net.lab0.kotlinexplorer.business.interactor.abstraction.GetAllChapters
import net.lab0.kotlinexplorer.mvi.Resource

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
