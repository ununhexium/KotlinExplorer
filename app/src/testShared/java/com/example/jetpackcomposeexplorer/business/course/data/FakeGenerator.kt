package com.example.jetpackcomposeexplorer.business.course.data

import com.example.jetpackcomposeexplorer.business.course.abstraction.Chapter
import com.example.jetpackcomposeexplorer.business.course.implementation.ChapterImpl
import com.example.jetpackcomposeexplorer.business.course.abstraction.LessonData
import com.example.jetpackcomposeexplorer.business.course.implementation.LessonDataImpl
import com.example.jetpackcomposeexplorer.business.course.implementation.LessonPage
import com.example.jetpackcomposeexplorer.business.course.abstraction.Module
import com.example.jetpackcomposeexplorer.business.course.implementation.ModuleImpl
import com.example.jetpackcomposeexplorer.business.course.abstraction.Prerequisite
import com.example.jetpackcomposeexplorer.business.course.abstraction.Theme
import com.example.jetpackcomposeexplorer.business.course.implementation.ThemeImpl
import com.example.jetpackcomposeexplorer.business.course.abstraction.CourseRepository


// TODO: the fake generator should remmeber the returned data for consistency
class FakeGenerator {

  companion object {
    val fakeLessonFinder = object : CourseRepository {
      override fun findLessonById(id: String): LessonData {
        return generateFakeLessonData(id = id)
      }

      override fun findChapterOf(lesson: LessonData): Chapter {
        TODO("Not yet implemented")
      }

      override fun findNextChapter(chapter: Chapter): Chapter {
        TODO("Not yet implemented")
      }

      override fun findThemeOf(lesson: LessonData): Theme {
        TODO("Not yet implemented")
      }
    }

    private var themeId = 0
    private var themeMap = mutableMapOf<String, Theme>()

    fun generateFakeTheme(
        id: String = "themeId${themeId++}",
        title: String = "Theme Title",
        moduleCount: Int = 3,
    ): Theme = themeMap.computeIfAbsent(id) {
      ThemeImpl(id, title, (1..moduleCount).map { generateFakeModule() })
    }

    private var moduleId = 0
    private var moduleMap = mutableMapOf<String, Module>()

    fun generateFakeModule(
        id: String = "moduleId${moduleId++}",
        title: String = "Module Title",
        chapterCount: Int = 3,
    ): Module = moduleMap.computeIfAbsent(id) {
      ModuleImpl(id, title, (1..chapterCount).map { generateFakeChapter() })
    }

    private var chapterId = 0
    private var chapterMap = mutableMapOf<String, Chapter>()

    fun generateFakeChapter(
        id: String = "chapterId${chapterId++}",
        title: String = "Chapter Title",
        description: String = "Chapter's description",
        lessonCount: Int = 3,
    ) = chapterMap.computeIfAbsent(id) {
      ChapterImpl(id, title, description, (1..lessonCount).map { generateFakeLessonData() })
    }

    private var lessonId = 0
    private var lessonMap = mutableMapOf<String, LessonData>()

    fun generateFakeLessonData(
        id: String = "lessonId${lessonId++}",
        title: String = "Lesson Title",
        pagesCount: Int = 3,
        dependencies: List<Prerequisite> = listOf(),
    ): LessonData = lessonMap.computeIfAbsent(id) {
      LessonDataImpl(id, title, (1..pagesCount).map { generateFakeLessonPage() }, dependencies)
    }

    private var pageId = 0
    private var pageMap = mutableMapOf<String, LessonPage>()

    fun generateFakeLessonPage(
        title: String = "lesson Page Title ${pageId++}",
        markdown: String = "*Markdown*",
    ): LessonPage = pageMap.computeIfAbsent(title) {
      LessonPage.InfoPage(title, markdown)
    }
  }
}
