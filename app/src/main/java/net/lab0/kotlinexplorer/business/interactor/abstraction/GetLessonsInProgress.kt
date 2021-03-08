package net.lab0.kotlinexplorer.business.interactor.abstraction

import kotlinx.coroutines.flow.Flow
import net.lab0.kotlinexplorer.business.domain.LessonProgress
import net.lab0.kotlinexplorer.mvi.Resource

interface GetLessonsInProgress {
  operator fun invoke(): Flow<Resource<List<LessonProgress>>>
}
