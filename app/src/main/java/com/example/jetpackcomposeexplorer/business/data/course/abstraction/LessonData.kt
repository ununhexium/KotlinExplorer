package com.example.jetpackcomposeexplorer.business.data.course.abstraction

import com.example.jetpackcomposeexplorer.business.data.course.implementation.LessonPage

interface LessonData: Prerequisite {
  /**
   * Unique identifier inside a chapter
   */
  override val id: String

  /**
   * The human name for this lesson
   */
  override val title: String

  /**
   * The pages of this lesson
   */
  val pages: List<LessonPage>

  /**
   * The lessons required to understand this one.
   */
  val dependencies: List<Prerequisite>
}
