package net.lab0.kotlinexplorer.business.domain

interface Lesson : Prerequisite {
  companion object {
    val EMPTY = object : Lesson {
      override val id = ""
      override val title = ""
      override val pages = listOf<LessonPage>()
      override val dependencies = listOf<Prerequisite>()
    }
  }

  /**
   * The identifier for this lesson. Must be unique.
   */
  override val id: String

  /**
   * The human name for this lesson
   */
  override val title: String

  /**
   * The pages of this lesson
   */
  val pages: List<LessonPage>

  /**
   * The lessons required to understand this one.
   */
  val dependencies: List<Prerequisite>
}
