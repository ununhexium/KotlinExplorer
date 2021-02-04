package com.example.jetpackcomposeexplorer.business.data.course.implementation

import com.example.jetpackcomposeexplorer.business.data.course.abstraction.Chapter
import com.example.jetpackcomposeexplorer.business.data.course.abstraction.CourseRepository
import com.example.jetpackcomposeexplorer.business.data.course.abstraction.LessonData
import com.example.jetpackcomposeexplorer.business.data.course.abstraction.Module
import com.example.jetpackcomposeexplorer.business.data.course.abstraction.Theme
import com.example.jetpackcomposeexplorer.business.data.course.ALL_THEMES

// TODO: build course tree indexes
class CourseRepositoryImpl(private val themes: List<Theme> = ALL_THEMES) : CourseRepository {

  override fun findThemeOf(lesson: LessonData): Theme {
    return findThemeOfLesson(lesson.id)
  }

  fun findThemeOf(chapter: Chapter): Theme {
    return findThemeOf(chapter.lessons.first())
  }

  fun findThemeOf(module: Module): Theme {
    return findThemeOf(module.chapters.first())
  }

  fun findThemeOfLesson(id: String): Theme {
    return themes.first { theme ->
      theme.modules.any { module ->
        module.chapters.any { chapter ->
          chapter.lessons.any {
            it.id == id
          }
        }
      }
    }
  }

  fun findModuleOf(chapter: Chapter): Module {
    return findThemeOf(chapter)
        .modules
        .first { module ->
          module.chapters.any { it == chapter }
        }
  }

  override fun findLessonById(id: String): LessonData {
    return findThemeOfLesson(id).modules.flatMap { module ->
      module.chapters.flatMap { chapter ->
        chapter.lessons.filter {
          it.id == id
        }
      }
    }.first()
  }

  override fun findNextChapter(chapter: Chapter): Chapter? {
    return findModuleOf(chapter)
        .chapters
        .dropWhile { it != chapter }
        .drop(1)
        .firstOrNull()
  }

  override fun findChapterOf(lesson: LessonData): Chapter {
    return findThemeOf(lesson)
        .modules
        .flatMap { it.chapters }
        .first { chapter -> chapter.lessons.any { it.id == lesson.id } }
  }
}
