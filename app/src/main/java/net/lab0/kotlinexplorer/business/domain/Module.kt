package net.lab0.kotlinexplorer.business.domain

interface Module: Prerequisite {
  /**
   * The title of this module
   */
  override val title:String

  /**
   * The chapters in this module
   */
  val chapters: List<Chapter>
}
