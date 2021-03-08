package net.lab0.kotlinexplorer.business.interactor.abstraction

import kotlinx.coroutines.flow.Flow
import net.lab0.kotlinexplorer.business.domain.feedback.LessonFeedback
import net.lab0.kotlinexplorer.mvi.Resource

interface GetLessonFeedback {
  suspend fun invoke(uid: String, lessonId: String): Flow<Resource<LessonFeedback?>>
}
