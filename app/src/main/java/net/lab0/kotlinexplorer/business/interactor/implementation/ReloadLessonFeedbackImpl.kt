package net.lab0.kotlinexplorer.business.interactor.implementation

import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import net.lab0.kotlinexplorer.business.domain.feedback.LessonFeedback
import net.lab0.kotlinexplorer.business.interactor.abstraction.ReloadLessonFeedback
import net.lab0.kotlinexplorer.framework.firebase.abstraction.LessonFeedbackService
import net.lab0.kotlinexplorer.mvi.Resource

class ReloadLessonFeedbackImpl(
  private val auth: FirebaseAuth,
  private val service: LessonFeedbackService,
) : ReloadLessonFeedback {
  override fun invoke(lessonId: String): Flow<Resource.LoadedResource<LessonFeedback?>> = flow {
    emit(
      Resource.LoadedResource(service.readUserFeedbackForLesson(auth.uid!!, lessonId))
    )
  }
}
