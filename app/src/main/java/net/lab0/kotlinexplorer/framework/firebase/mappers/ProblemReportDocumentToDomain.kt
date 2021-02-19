package net.lab0.kotlinexplorer.framework.firebase.mappers

import net.lab0.kotlinexplorer.business.domain.problemreport.ProblemReport
import net.lab0.kotlinexplorer.framework.firebase.model.ProblemReportDocument
import net.lab0.kotlinexplorer.framework.util.ToDomain
import java.util.*

class ProblemReportDocumentToDomain : ToDomain<ProblemReportDocument, ProblemReport> {
  override fun invoke(entity: ProblemReportDocument) =
      ProblemReport(
          entity.problemLocationDescription,
          entity.userFeedback,
          entity.createdAt.toDate(),
          UUID.fromString(entity.id),
      )
}
