package net.lab0.kotlinexplorer.business.interactor.abstraction

import kotlinx.coroutines.flow.Flow
import net.lab0.kotlinexplorer.business.domain.feedback.LessonFeedback
import net.lab0.kotlinexplorer.mvi.Resource

interface SendLessonFeedback {
  operator fun invoke(lessonFeedback: LessonFeedback): Flow<Resource<*>>
}
