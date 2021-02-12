package net.lab0.jetpackcomposeexplorer.business.interactor.implementation

import net.lab0.jetpackcomposeexplorer.business.domain.LessonProgress
import net.lab0.jetpackcomposeexplorer.business.interactor.abstraction.SaveLessonProgress
import net.lab0.jetpackcomposeexplorer.business.persistence.abstraction.LessonProgressDataSource
import net.lab0.jetpackcomposeexplorer.mvi.Resource
import net.lab0.jetpackcomposeexplorer.utils.printLogD
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
