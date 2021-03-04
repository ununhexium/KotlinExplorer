package net.lab0.kotlinexplorer.framework.firebase.implementation

import com.google.firebase.firestore.FirebaseFirestore
import net.lab0.kotlinexplorer.business.domain.extracontent.ExtraContentRequest
import net.lab0.kotlinexplorer.framework.firebase.abstraction.ExtraContentService
import net.lab0.kotlinexplorer.framework.firebase.model.ExtraContentRequestDocument
import net.lab0.kotlinexplorer.framework.firebase.model.extraLessonRequestDocument
import net.lab0.kotlinexplorer.framework.util.FromDomain

class ExtraContentServiceImpl(
    private val firestore: FirebaseFirestore,
    private val fromDomain: FromDomain<ExtraContentRequestDocument, ExtraContentRequest>,
) : ExtraContentService {
  override suspend fun requestExtraLessons(uid: String, extra: ExtraContentRequest) {
    firestore
        .extraLessonRequestDocument(uid)
        .set(fromDomain(extra))
  }
}
