package net.lab0.kotlinexplorer.business.domain.feedback

enum class LessonDuration(val code: String) {
  TOO_SHORT("TOO_SHORT"),
  BALANCED_DURATION("GOOD"),
  TOO_LONG("TOO_LONG"),
  UNSET("UNSET"),
}
