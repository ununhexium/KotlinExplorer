package net.lab0.kotlinexplorer.business.interactor.abstraction

import net.lab0.kotlinexplorer.business.domain.Chapter
import net.lab0.kotlinexplorer.mvi.Resource
import kotlinx.coroutines.flow.Flow

interface GetAllChapters {
  operator fun invoke(): Flow<Resource<List<Chapter>>>
}
