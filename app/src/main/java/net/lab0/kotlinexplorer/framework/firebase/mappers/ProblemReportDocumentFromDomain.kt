package net.lab0.kotlinexplorer.framework.firebase.mappers

import com.google.firebase.Timestamp
import net.lab0.kotlinexplorer.business.domain.problemreport.ProblemReport
import net.lab0.kotlinexplorer.framework.firebase.model.ProblemReportDocument
import net.lab0.kotlinexplorer.framework.util.FromDomain

class ProblemReportDocumentFromDomain : FromDomain<ProblemReportDocument, ProblemReport> {
  override fun invoke(domain: ProblemReport) =
      ProblemReportDocument(
          domain.id.toString(),
          Timestamp(domain.createdAt),
          domain.problemLocationDescription,
          domain.userFeedback,
          domain.status,
      )
}
