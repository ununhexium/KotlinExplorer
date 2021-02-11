package com.example.jetpackcomposeexplorer.business.interactor.implementation

import com.example.jetpackcomposeexplorer.business.course.data.kotlin.KOTLIN
import com.example.jetpackcomposeexplorer.business.domain.Chapter
import com.example.jetpackcomposeexplorer.business.interactor.abstraction.GetAllChapters
import com.example.jetpackcomposeexplorer.mvi.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetAllChaptersImpl : GetAllChapters {
  override fun invoke(): Flow<Resource<List<Chapter>>> =
    flow {
      emit(
          Resource.LoadedResource(
              KOTLIN
          )
      )
    }
}
