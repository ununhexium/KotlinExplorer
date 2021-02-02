package com.example.jetpackcomposeexplorer.business.course.abstraction

interface CourseRepository {
  fun findChapterOf(lesson: LessonData): Chapter
  fun findThemeOf(lesson: LessonData): Theme

  fun findLessonById(id: String): LessonData

  /**
   * @return The next chapter in the module, if it exists
   */
  fun findNextChapter(chapter: Chapter): Chapter?
}
