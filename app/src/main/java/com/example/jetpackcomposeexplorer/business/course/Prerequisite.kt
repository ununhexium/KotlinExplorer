package com.example.jetpackcomposeexplorer.business.course

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
