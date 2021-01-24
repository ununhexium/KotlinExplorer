package com.example.jetpackcomposeexplorer.business.course.data.kotlin

import com.example.jetpackcomposeexplorer.business.course.Chapter
import com.example.jetpackcomposeexplorer.business.course.LessonData
import com.example.jetpackcomposeexplorer.business.course.Module
import org.reflections.Reflections
import org.reflections.scanners.SubTypesScanner
import org.reflections.util.ClasspathHelper
import org.reflections.util.ConfigurationBuilder

class LessonFinderImpl : LessonFinder {
  val reflections = Reflections(
      ConfigurationBuilder()
          .setUrls(ClasspathHelper.forClass(LessonFinderImpl::class.java))
          .setScanners(SubTypesScanner())
  )

  override fun findLessonById(id: String): LessonData {
    return reflections.getSubTypesOf(LessonData::class.java).mapNotNull {
      it.kotlin.objectInstance
    }.first {
      it.id == id
    }
  }

  override fun findLessonsInChapter(chapter: Chapter): List<LessonData> {
    return reflections.getSubTypesOf(LessonData::class.java).mapNotNull {
      it.kotlin.objectInstance
    }.filter {
      it.chapter == chapter
    }
  }

  override fun findChaptersInModule(module: Module): List<Chapter> {
    return reflections.getSubTypesOf(Chapter::class.java).mapNotNull {
      it.kotlin.objectInstance
    }.filter {
      it.module == module
    }
  }
}
