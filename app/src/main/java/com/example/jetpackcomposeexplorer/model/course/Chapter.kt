package com.example.jetpackcomposeexplorer.model.course

interface Chapter: Prerequisite {
  /**
   * Unique identifier for this chapter
   */
  override val id: String

  /**
   * A preview of this chapter's content
   */
  val description: String

  /**
   * The lessons contained in this chapter
   */
  val lessons: List<Prerequisite>
}
