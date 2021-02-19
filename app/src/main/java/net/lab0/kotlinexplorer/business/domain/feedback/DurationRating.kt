package net.lab0.kotlinexplorer.business.domain.feedback

enum class DurationRating(val code: String) {
  TOO_SHORT("TOO_SHORT"),
  BALANCED("BALANCED"),
  TOO_LONG("TOO_LONG"),
  UNSET("UNSET"),
}
