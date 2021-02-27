package net.lab0.kotlinexplorer.business.interactor.implementation

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import net.lab0.kotlinexplorer.business.domain.extracontent.ExtraContentRequest
import net.lab0.kotlinexplorer.business.interactor.abstraction.RequestExtraLessons
import net.lab0.kotlinexplorer.business.persistence.abstraction.LessonProgressDataSource
import net.lab0.kotlinexplorer.framework.firebase.abstraction.ExtraContentService
import net.lab0.kotlinexplorer.mvi.Resource

class RequestExtraLessonsImpl(
    private val extraContentService: ExtraContentService,
    private val dataSource: LessonProgressDataSource,
) : RequestExtraLessons {
  override fun invoke(
      uid: String,
  ): Flow<Resource.LoadedResource<Unit>> = flow {
    val inProgress = dataSource.getLessonsInProgress()
    val extra = ExtraContentRequest(
        inProgress.sumBy { it.successCount },
        inProgress.sumBy { it.failureCount },
    )
    extraContentService.requestExtraLessons(uid, extra)
    emit(Resource.LoadedResource(Unit))
  }
}
