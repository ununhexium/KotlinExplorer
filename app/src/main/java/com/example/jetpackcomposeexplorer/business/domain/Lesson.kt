package com.example.jetpackcomposeexplorer.business.domain

interface Lesson: Prerequisite {
  /**
   * The identifier for this lesson. Must be unique.
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
