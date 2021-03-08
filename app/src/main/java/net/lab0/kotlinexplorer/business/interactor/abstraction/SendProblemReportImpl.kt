package net.lab0.kotlinexplorer.business.interactor.abstraction

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import net.lab0.kotlinexplorer.business.domain.problemreport.ProblemReport
import net.lab0.kotlinexplorer.framework.firebase.abstraction.ProblemReportService
import net.lab0.kotlinexplorer.mvi.Resource

class SendProblemReportImpl(
  private val problemReportService: ProblemReportService
) : SendProblemReport {
  override suspend fun invoke(problemReport: ProblemReport): Flow<Resource.EmptyLoadedResource> =
    flow {
      problemReportService.insertOrUpdateProblemReport(problemReport)
      emit(Resource.EmptyLoadedResource)
    }
}
