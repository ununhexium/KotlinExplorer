package com.example.jetpackcomposeexplorer.business.domain

import com.example.jetpackcomposeexplorer.business.course.data.kotlin.KOTLIN

object LessonBrowser {
  fun getLessonById(lessonId: String): Lesson =
      KOTLIN.flatMap { it.lessons }.first { it.id == lessonId }

  fun getChapterForLesson(lessonId: String): Chapter? =
      KOTLIN.firstOrNull { it.lessons.any { it.id == lessonId } }
}
