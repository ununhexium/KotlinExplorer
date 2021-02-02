package com.example.jetpackcomposeexplorer.business.course.abstraction

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
