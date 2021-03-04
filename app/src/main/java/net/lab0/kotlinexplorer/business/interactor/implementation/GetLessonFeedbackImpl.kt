package net.lab0.kotlinexplorer.business.interactor.implementation

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import net.lab0.kotlinexplorer.business.domain.feedback.LessonFeedback
import net.lab0.kotlinexplorer.business.interactor.abstraction.GetLessonFeedback
import net.lab0.kotlinexplorer.framework.firebase.abstraction.LessonFeedbackService
import net.lab0.kotlinexplorer.mvi.Resource

class GetLessonFeedbackImpl(
    private val lessonFeedbackService: LessonFeedbackService
) : GetLessonFeedback {
  override suspend fun invoke(uid:String, lessonId: String): Flow<Resource<LessonFeedback?>> = flow {
    val feedback = lessonFeedbackService.readAllUserFeedbacks(uid).sortedByDescending { it.timestamp }.firstOrNull()
    emit(Resource.LoadedResource(feedback))
  }
}
