package net.lab0.kotlinexplorer.business.interactor.abstraction

import net.lab0.kotlinexplorer.business.domain.LessonProgress
import net.lab0.kotlinexplorer.mvi.Resource
import kotlinx.coroutines.flow.Flow

interface GetLessonsInProgress {
  operator fun invoke(): Flow<Resource<List<LessonProgress>>>
}
