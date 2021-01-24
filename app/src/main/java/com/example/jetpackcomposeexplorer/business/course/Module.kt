package com.example.jetpackcomposeexplorer.business.course

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
   * The theme this Module belongs to.
   */
  val theme: Theme
}
