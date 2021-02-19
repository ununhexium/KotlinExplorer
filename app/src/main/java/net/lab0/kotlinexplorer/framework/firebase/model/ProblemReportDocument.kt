package net.lab0.kotlinexplorer.framework.firebase.model

import com.google.firebase.Timestamp

data class ProblemReportDocument(
    val id: String = "",
    val createdAt: Timestamp = Timestamp(0,0),
    val problemLocationDescription: String = "",
    val userFeedback: String = "",
)
