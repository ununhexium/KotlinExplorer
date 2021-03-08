package net.lab0.kotlinexplorer.business.interactor.abstraction

import kotlinx.coroutines.flow.Flow
import net.lab0.kotlinexplorer.business.domain.problemreport.ProblemReport
import net.lab0.kotlinexplorer.mvi.Resource

interface SendProblemReport {
  suspend operator fun invoke(problemReport: ProblemReport): Flow<Resource.EmptyLoadedResource>
}
