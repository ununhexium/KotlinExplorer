package net.lab0.kotlinexplorer.framework.firebase.abstraction

import kotlinx.coroutines.flow.Flow
import net.lab0.kotlinexplorer.business.domain.extracontent.ExtraContentRequest
import net.lab0.kotlinexplorer.mvi.Resource

interface ExtraContentService {
  suspend fun requestExtraLessons(
      uid: String,
      extra: ExtraContentRequest
  ): Flow<Resource.EmptyLoadedResource>
}
