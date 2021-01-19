package com.example.jetpackcomposeexplorer.model.course

interface Lesson: Prerequisite {
  /**
   * The identifier for this lesson. Must be unique.
   */
  override val id: String

  /**
   * The pages of this lesson
   */
  val pages: List<LessonPage>

  /**
   * The lessons required to understand this one.
   */
  val dependencies: List<Prerequisite>
}