package net.lab0.kotlinexplorer.framework.firebase.implementation

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import net.lab0.kotlinexplorer.business.domain.problemreport.ProblemReport
import net.lab0.kotlinexplorer.framework.firebase.abstraction.ProblemReportService
import net.lab0.kotlinexplorer.framework.firebase.model.ProblemReportDocument
import net.lab0.kotlinexplorer.framework.firebase.model.problemReportCollection
import net.lab0.kotlinexplorer.framework.util.FromDomain

class ProblemReportServiceImpl(
    private val firebaseAuth: FirebaseAuth,
    private val firestore: FirebaseFirestore,
    private val fromDomain: FromDomain<ProblemReportDocument, ProblemReport>,
) : ProblemReportService {
  override suspend fun insertOrUpdateProblemReport(problemReport: ProblemReport) {
    val uid = firebaseAuth.uid
    val document = fromDomain(problemReport)

    if (uid != null) {
      firestore.problemReportCollection(firebaseAuth.uid!!).add(document)
    }
  }
}
