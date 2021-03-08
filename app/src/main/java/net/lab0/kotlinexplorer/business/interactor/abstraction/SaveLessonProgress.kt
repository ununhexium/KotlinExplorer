package net.lab0.kotlinexplorer.business.interactor.abstraction

import kotlinx.coroutines.flow.Flow
import net.lab0.kotlinexplorer.business.domain.LessonProgress
import net.lab0.kotlinexplorer.mvi.Resource

interface SaveLessonProgress {
  operator fun invoke(
    lessonProgress: LessonProgress,
  ): Flow<Resource<Nothing>>
}
