package net.lab0.kotlinexplorer.business.interactor.abstraction

import kotlinx.coroutines.flow.Flow
import net.lab0.kotlinexplorer.mvi.Resource

interface RequestExtraLessons {
  operator fun invoke(): Flow<Resource.LoadedResource<Unit>>
}
