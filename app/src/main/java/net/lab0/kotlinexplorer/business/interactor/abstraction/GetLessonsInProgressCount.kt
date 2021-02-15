package net.lab0.kotlinexplorer.business.interactor.abstraction

import net.lab0.kotlinexplorer.mvi.Resource
import kotlinx.coroutines.flow.Flow

interface GetLessonsInProgressCount {
  operator fun invoke(): Flow<Resource<Int>>
}

