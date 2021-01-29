package com.example.jetpackcomposeexplorer.business.course

class FinderImpl(private val theme: Theme) : Finder {
  override fun findLessonById(id: String): LessonData {
    return theme.modules.flatMap { module ->
      module.chapters.flatMap { chapter ->
        chapter.lessons.filter {
          it.id == id
        }
      }
    }.first()
  }

  override fun findLessonsInChapter(chapter: Chapter): List<LessonData> {
    return theme.modules.flatMap { module ->
      module.chapters.filter { it == chapter }.flatMap { chapter ->
        chapter.lessons
      }
    }
  }

  override fun findChaptersInModule(module: Module): List<Chapter> {
    return theme
        .modules
        .filter { it == module }
        .flatMap { it.chapters }
  }

  override fun findChapterOf(lesson: LessonData): Chapter {
    return theme
        .modules
        .map { module ->
          module.chapters.first {
            it.lessons.any { it == lesson }
          }
        }.first()
  }
}
