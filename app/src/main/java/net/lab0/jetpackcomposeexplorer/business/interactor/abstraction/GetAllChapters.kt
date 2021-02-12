package net.lab0.jetpackcomposeexplorer.business.interactor.abstraction

import net.lab0.jetpackcomposeexplorer.business.domain.Chapter
import net.lab0.jetpackcomposeexplorer.mvi.Resource
import kotlinx.coroutines.flow.Flow

interface GetAllChapters {
  operator fun invoke(): Flow<Resource<List<Chapter>>>
}
