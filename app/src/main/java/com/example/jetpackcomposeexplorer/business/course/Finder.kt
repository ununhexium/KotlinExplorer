package com.example.jetpackcomposeexplorer.business.course

interface Finder {
  fun findLessonById(id: String): LessonData
  fun findLessonsInChapter(chapter: Chapter): List<LessonData>
  fun findChaptersInModule(module: Module): List<Chapter>
  fun findChapterOf(lesson: LessonData): Chapter
}
