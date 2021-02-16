package net.lab0.kotlinexplorer.business.domain

import net.lab0.kotlinexplorer.business.course.data.kotlin.KOTLIN

object LessonBrowser {
  fun getLessonById(lessonId: String): Lesson =
      KOTLIN.flatMap { it.lessons }.first { it.id == lessonId }

  fun getChapterForLesson(lessonId: String): Chapter? =
      KOTLIN.firstOrNull { it.lessons.any { it.id == lessonId } }

  fun getNextLessonInChapter(lessonId: String): Lesson? =
      getChapterForLesson(lessonId)
          ?.lessons
          ?.dropWhile { it.id != lessonId }
          ?.drop(1)
          ?.firstOrNull()
}
