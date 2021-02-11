package com.example.jetpackcomposeexplorer.business.interactor.implementation

import com.example.jetpackcomposeexplorer.business.domain.LessonProgress
import com.example.jetpackcomposeexplorer.business.interactor.abstraction.SaveLessonProgress
import com.example.jetpackcomposeexplorer.business.persistence.abstraction.LessonProgressDataSource
import com.example.jetpackcomposeexplorer.mvi.Resource
import com.example.jetpackcomposeexplorer.utils.printLogD
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class SaveLessonProgressImpl(
    private val dataSource: LessonProgressDataSource,
) : SaveLessonProgress {
  override fun invoke(
      lessonProgress: LessonProgress,
  ): Flow<Resource<Nothing>> = flow {
    printLogD(this::class.java.simpleName, "Saving lesson progress: $lessonProgress")
    dataSource.saveLessonProgress(lessonProgress)
    emit(Resource.Empty)
  }
}
