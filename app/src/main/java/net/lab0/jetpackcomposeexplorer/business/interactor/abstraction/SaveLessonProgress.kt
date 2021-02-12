package net.lab0.jetpackcomposeexplorer.business.interactor.abstraction

import net.lab0.jetpackcomposeexplorer.business.domain.LessonProgress
import net.lab0.jetpackcomposeexplorer.mvi.Resource
import kotlinx.coroutines.flow.Flow

interface SaveLessonProgress {
  operator fun invoke(
      lessonProgress: LessonProgress,
  ): Flow<Resource<Nothing>>
}
