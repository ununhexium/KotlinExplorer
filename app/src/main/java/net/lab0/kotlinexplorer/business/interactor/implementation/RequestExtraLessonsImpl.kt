package net.lab0.kotlinexplorer.business.interactor.implementation

import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import net.lab0.kotlinexplorer.business.domain.extracontent.ExtraContentRequest
import net.lab0.kotlinexplorer.business.interactor.abstraction.RequestExtraLessons
import net.lab0.kotlinexplorer.business.persistence.abstraction.LessonProgressDataSource
import net.lab0.kotlinexplorer.framework.firebase.abstraction.ExtraContentService
import net.lab0.kotlinexplorer.mvi.Resource
import net.lab0.kotlinexplorer.utils.printLogD

class RequestExtraLessonsImpl(
  private val auth: FirebaseAuth,
  private val extraContentService: ExtraContentService,
  private val dataSource: LessonProgressDataSource,
) : RequestExtraLessons {

  override fun invoke(
    liking: String?,
    reason: String?,
    comment: String?
  ): Flow<Resource.LoadedResource<Unit>> = flow {
    val inProgress = dataSource.getLessonsInProgress()

    val extra = ExtraContentRequest(
      inProgress.sumBy { it.successCount },
      inProgress.sumBy { it.failureCount },
      liking,
      reason,
      comment,
    )

    extraContentService.requestExtraLessons(auth.uid!!, extra)

    emit(Resource.LoadedResource(Unit))
  }
}
