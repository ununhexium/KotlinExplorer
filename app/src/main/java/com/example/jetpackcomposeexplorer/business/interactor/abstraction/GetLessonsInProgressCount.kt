package com.example.jetpackcomposeexplorer.business.interactor.abstraction

import com.example.jetpackcomposeexplorer.business.domain.LessonProgress
import com.example.jetpackcomposeexplorer.business.domain.state.DataState
import com.example.jetpackcomposeexplorer.mvi.Resource
import kotlinx.coroutines.flow.Flow

interface GetLessonsInProgressCount {
  operator fun invoke(): Flow<Resource<Int>>
}

