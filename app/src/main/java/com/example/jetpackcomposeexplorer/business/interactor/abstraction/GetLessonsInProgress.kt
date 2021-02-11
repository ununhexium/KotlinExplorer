package com.example.jetpackcomposeexplorer.business.interactor.abstraction

import com.example.jetpackcomposeexplorer.business.domain.LessonProgress
import com.example.jetpackcomposeexplorer.mvi.Resource
import kotlinx.coroutines.flow.Flow

interface GetLessonsInProgress {
  operator fun invoke(): Flow<Resource<List<LessonProgress>>>
}
