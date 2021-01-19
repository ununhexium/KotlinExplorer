package com.example.jetpackcomposeexplorer.model.course

interface Lesson {
  /**
   * The identifier for this lesson. Must be unique.
   */
  val id: Int

  /**
   * The pages of this lesson
   */
  val pages: List<LessonPage>

  /**
   * The lessons required to understand this one.
   */
  val dependencies: List<Lesson>
}