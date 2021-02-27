package net.lab0.kotlinexplorer.framework.firebase.mappers

import net.lab0.kotlinexplorer.business.domain.extracontent.ExtraContentRequest
import net.lab0.kotlinexplorer.framework.firebase.model.ExtraContentRequestDocument
import net.lab0.kotlinexplorer.framework.util.FromDomain

class ExtraContentRequestFromDomain : FromDomain<ExtraContentRequestDocument, ExtraContentRequest> {
  override fun invoke(domain: ExtraContentRequest): ExtraContentRequestDocument {
    return ExtraContentRequestDocument(
        domain.globalSuccess,
        domain.globalFailure,
    )
  }
}
