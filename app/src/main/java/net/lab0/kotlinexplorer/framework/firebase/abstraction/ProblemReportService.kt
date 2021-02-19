package net.lab0.kotlinexplorer.framework.firebase.abstraction

import net.lab0.kotlinexplorer.business.domain.problemreport.ProblemReport

interface ProblemReportService {
  suspend fun insertOrUpdateProblemReport(problemReport: ProblemReport)
}
