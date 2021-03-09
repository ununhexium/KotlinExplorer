package net.lab0.kotlinexplorer.business.domain.extracontent

data class ExtraContentRequest(
  val globalSuccess: Int,
  val globalFailure: Int,
  val liking: String?,
  val reason: String?,
  val comment: String?,
)
