package net.lab0.kotlinexplorer.business.interactor.abstraction

import kotlinx.coroutines.flow.Flow
import net.lab0.kotlinexplorer.business.domain.Chapter
import net.lab0.kotlinexplorer.mvi.Resource

interface GetAllChapters {
  operator fun invoke(): Flow<Resource<List<Chapter>>>
}
