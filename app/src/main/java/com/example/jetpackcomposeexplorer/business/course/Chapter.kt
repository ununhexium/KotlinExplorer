package com.example.jetpackcomposeexplorer.business.course

interface Chapter: Prerequisite {
  /**
   * Unique identifier inside a module
   */
  override val id: String

  /**
   * Title of the chapter
   */
  override val title: String

  /**
   * The module this chapter belongs to
   */
  val module: Module

  /**
   * A preview of this chapter's content
   */
  val description: String
}
