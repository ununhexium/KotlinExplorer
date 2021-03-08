package net.lab0.kotlinexplorer.framework.firebase.model

import com.google.firebase.Timestamp

data class ProblemReportDocument(
  val id: String,
  val createdAt: Timestamp,
  val problemLocationDescription: String,
  val userFeedback: String,
  val status: String,
) {
  constructor(
  ) : this(
    "",
    Timestamp(0, 0),
    "",
    "",
    "",
  )
}
