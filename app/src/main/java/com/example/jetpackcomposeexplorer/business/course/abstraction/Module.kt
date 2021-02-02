package com.example.jetpackcomposeexplorer.business.course.abstraction

interface Module: Prerequisite {
  /**
   * Unique identifier inside a
   */
  override val id: String

  /**
   * The title of this module
   */
  override val title:String

  /**
   * The chapters in this module
   */
  val chapters: List<Chapter>
}
