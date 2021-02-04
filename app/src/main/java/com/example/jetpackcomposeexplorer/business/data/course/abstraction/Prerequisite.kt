package com.example.jetpackcomposeexplorer.business.data.course.abstraction

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
