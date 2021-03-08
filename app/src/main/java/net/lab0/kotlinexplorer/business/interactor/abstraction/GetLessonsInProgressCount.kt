package net.lab0.kotlinexplorer.business.interactor.abstraction

import kotlinx.coroutines.flow.Flow
import net.lab0.kotlinexplorer.mvi.Resource

interface GetLessonsInProgressCount {
  operator fun invoke(): Flow<Resource<Int>>
}

