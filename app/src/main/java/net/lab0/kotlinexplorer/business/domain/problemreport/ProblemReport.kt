package net.lab0.kotlinexplorer.business.domain.problemreport

import java.util.*

data class ProblemReport(
    val problemLocationDescription: String,
    val userFeedback: String,
    val createdAt: Date = Date(),
    val id: UUID = UUID.randomUUID(),
)
