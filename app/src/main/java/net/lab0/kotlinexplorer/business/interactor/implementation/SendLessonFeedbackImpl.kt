package net.lab0.kotlinexplorer.business.interactor.implementation

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import net.lab0.kotlinexplorer.business.domain.feedback.LessonFeedback
import net.lab0.kotlinexplorer.business.interactor.abstraction.SendLessonFeedback
import net.lab0.kotlinexplorer.framework.firebase.abstraction.LessonFeedbackService
import net.lab0.kotlinexplorer.mvi.Resource

class SendLessonFeedbackImpl(
    private val lessonFeedbackService: LessonFeedbackService,
) : SendLessonFeedback {
  override fun invoke(lessonFeedback: LessonFeedback): Flow<Resource<*>> = flow {
    lessonFeedbackService.insertOrUpdateFeedback(lessonFeedback)
    emit(Resource.EmptyLoadedResource)
  }
}
