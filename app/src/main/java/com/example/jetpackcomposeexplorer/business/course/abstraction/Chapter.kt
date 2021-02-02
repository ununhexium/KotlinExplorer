package com.example.jetpackcomposeexplorer.business.course.abstraction

interface Chapter: Prerequisite {
  /**
   * Unique identifier inside a module
   */
  override val id: String

  /**
   * Title of the chapter
   */
  override val title: String

  val lessons: List<LessonData>

  /**
   * A preview of this chapter's content
   */
  val description: String
}
