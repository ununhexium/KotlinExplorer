package net.lab0.kotlinexplorer.business.interactor.abstraction

import kotlinx.coroutines.flow.Flow
import net.lab0.kotlinexplorer.mvi.Resource

interface RequestExtraLessons {
  operator fun invoke(
    liking: String?,
    reason: String?,
    comment: String?
  ): Flow<Resource.LoadedResource<Unit>>
}
