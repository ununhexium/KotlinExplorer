package net.lab0.kotlinexplorer.business.domain

interface Prerequisite {
  /**
   * Unique identifier
   */
  val id: String

  /**
   * Human readable id
   */
  val title: String
}
