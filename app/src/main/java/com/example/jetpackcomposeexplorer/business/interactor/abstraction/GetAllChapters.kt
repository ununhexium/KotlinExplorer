package com.example.jetpackcomposeexplorer.business.interactor.abstraction

import com.example.jetpackcomposeexplorer.business.domain.Chapter
import com.example.jetpackcomposeexplorer.mvi.Resource
import kotlinx.coroutines.flow.Flow

interface GetAllChapters {
  operator fun invoke(): Flow<Resource<List<Chapter>>>
}
