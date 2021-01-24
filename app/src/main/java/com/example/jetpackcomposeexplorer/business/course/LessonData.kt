package com.example.jetpackcomposeexplorer.business.course

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
   * The chapter this lesson belongs to.
   */
  val chapter: Chapter

  /**
   * The pages of this lesson
   */
  val pages: List<LessonPage>

  /**
   * The lessons required to understand this one.
   */
  val dependencies: List<Prerequisite>
}
