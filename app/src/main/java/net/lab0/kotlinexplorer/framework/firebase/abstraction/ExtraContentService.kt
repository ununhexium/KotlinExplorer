package net.lab0.kotlinexplorer.framework.firebase.abstraction

import net.lab0.kotlinexplorer.business.domain.extracontent.ExtraContentRequest

interface ExtraContentService {
  suspend fun requestExtraLessons(
    uid: String,
    extra: ExtraContentRequest
  )
}
