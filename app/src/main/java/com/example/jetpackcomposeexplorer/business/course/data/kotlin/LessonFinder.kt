package com.example.jetpackcomposeexplorer.business.course.data.kotlin

import com.example.jetpackcomposeexplorer.business.course.Chapter
import com.example.jetpackcomposeexplorer.business.course.LessonData
import com.example.jetpackcomposeexplorer.business.course.Module

interface LessonFinder {
  fun findLessonById(id: String): LessonData
  fun findLessonsInChapter(chapter: Chapter): List<LessonData>
  fun findChaptersInModule(module: Module): List<Chapter>
}