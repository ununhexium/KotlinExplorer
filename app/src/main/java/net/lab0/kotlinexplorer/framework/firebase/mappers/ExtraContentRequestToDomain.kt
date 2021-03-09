package net.lab0.kotlinexplorer.framework.firebase.mappers

import net.lab0.kotlinexplorer.business.domain.extracontent.ExtraContentRequest
import net.lab0.kotlinexplorer.framework.firebase.model.ExtraContentRequestDocument
import net.lab0.kotlinexplorer.framework.util.ToDomain

class ExtraContentRequestToDomain : ToDomain<ExtraContentRequestDocument, ExtraContentRequest> {
  override fun invoke(entity: ExtraContentRequestDocument): ExtraContentRequest {
    return ExtraContentRequest(
      entity.globalSuccess,
      entity.globalFailure,
      entity.liking,
      entity.reason,
      entity.comment,
    )
  }
}
