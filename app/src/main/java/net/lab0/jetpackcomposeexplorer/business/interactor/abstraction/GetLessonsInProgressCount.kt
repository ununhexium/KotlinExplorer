package net.lab0.jetpackcomposeexplorer.business.interactor.abstraction

import net.lab0.jetpackcomposeexplorer.mvi.Resource
import kotlinx.coroutines.flow.Flow

interface GetLessonsInProgressCount {
  operator fun invoke(): Flow<Resource<Int>>
}

