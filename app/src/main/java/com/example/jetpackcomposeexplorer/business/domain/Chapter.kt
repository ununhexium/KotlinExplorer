package com.example.jetpackcomposeexplorer.business.domain

interface Chapter: Prerequisite {
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
  val lessons: List<Prerequisite>
}
