package net.lab0.kotlinexplorer.business.interactor.implementation

import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import net.lab0.kotlinexplorer.business.domain.feedback.DifficultyRating
import net.lab0.kotlinexplorer.business.domain.feedback.DurationRating
import net.lab0.kotlinexplorer.business.domain.feedback.LessonFeedback
import net.lab0.kotlinexplorer.business.interactor.abstraction.SendLessonFeedback
import net.lab0.kotlinexplorer.framework.firebase.abstraction.LessonFeedbackService
import net.lab0.kotlinexplorer.mvi.Resource

class SendLessonFeedbackImpl(
  private val auth: FirebaseAuth,
  private val lessonFeedbackService: LessonFeedbackService,
) : SendLessonFeedback {
  override fun invoke(lessonFeedback: LessonFeedback): Flow<Resource<*>> = flow {
    if (lessonFeedback.difficultyRating != DifficultyRating.UNSET || lessonFeedback.durationRating != DurationRating.UNSET) {
      lessonFeedbackService.insertOrUpdateFeedback(auth.uid!!, lessonFeedback)
    }
    emit(Resource.EmptyLoadedResource)
  }
}
