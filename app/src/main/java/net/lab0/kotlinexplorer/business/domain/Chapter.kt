package net.lab0.kotlinexplorer.business.domain

interface Chapter : Prerequisite {
  companion object {
    val EMPTY = object : Chapter {
      override val id = ""
      override val title = ""
      override val description = ""
      override val lessons = listOf<Lesson>()
    }
  }

  /**
   * Unique identifier for this chapter
   */
  override val id: String

  /**
   * Title of the chapter
   */
  override val title: String

  /**
   * A preview of this chapter's content
   */
  val description: String

  /**
   * The lessons contained in this chapter
   */
  val lessons: List<Lesson>
}
