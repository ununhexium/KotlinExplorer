package com.example.jetpackcomposeexplorer.framework.datasource

import com.example.jetpackcomposeexplorer.business.course.Chapter
import com.example.jetpackcomposeexplorer.business.course.ChapterImpl
import com.example.jetpackcomposeexplorer.business.course.LessonData
import com.example.jetpackcomposeexplorer.business.course.LessonDataImpl
import com.example.jetpackcomposeexplorer.business.course.LessonPage
import com.example.jetpackcomposeexplorer.business.course.Module
import com.example.jetpackcomposeexplorer.business.course.ModuleImpl
import com.example.jetpackcomposeexplorer.business.course.Prerequisite
import com.example.jetpackcomposeexplorer.business.course.Theme
import com.example.jetpackcomposeexplorer.business.course.ThemeImpl
import com.example.jetpackcomposeexplorer.business.course.data.kotlin.LessonFinder
import com.example.jetpackcomposeexplorer.business.domain.Lesson

// TODO: the fake generator should remmeber the returned data for consistency
class FakeGenerator {
  companion object {
    val fakeLessonFinder = object : LessonFinder {
      override fun findLessonById(id: String): LessonData {
        return generateFakeLessonData(id = id)
      }

      override fun findLessonsInChapter(chapter: Chapter): List<LessonData> {
        TODO("Not yet implemented")
      }

      override fun findChaptersInModule(module: Module): List<Chapter> {
        TODO("Not yet implemented")
      }

    }

    fun generateFakeTheme(
        id: String = "themeId",
        title: String = "Theme Title",
    ): Theme = ThemeImpl(id, title)

    fun generateFakeModule(
        id: String = "moduleId",
        title: String = "Module Title",
        theme: Theme = generateFakeTheme(),
    ): Module = ModuleImpl(id, title, theme)

    fun generateFakeChapter(
        id: String = "chapterId",
        title: String = "Chapter Title",
        description: String = "Chapter's description",
        module: Module = generateFakeModule(),
    ) = ChapterImpl(id, title, description, module)

    fun generateFakeLessonData(
        id: String = "lessonId",
        title: String = "Lesson Title",
        chapter: Chapter = generateFakeChapter(),
        pages: List<LessonPage> = listOf(
            generateFakeLessonPage()
        ),
        dependencies: List<Prerequisite> =
            if (id != "lessonId") {
              listOf(generateFakeLessonData())
            } else listOf(),
    ): LessonData =
        LessonDataImpl(id,
            title,
            chapter,
            pages,
            dependencies
        )

    fun generateFakeLessonData(
        id: String = "lessonId",
        title: String = "Lesson Title",
        chapter: Chapter = generateFakeChapter(),
        pagesCount: Int,
        dependencies: List<Prerequisite> =
            if (id != "lessonId") {
              listOf(generateFakeLessonData())
            } else listOf(),
    ): LessonData =
        generateFakeLessonData(
            id,
            title,
            chapter,
            (1..pagesCount).map {
              generateFakeLessonPage()
            },
            dependencies,
        )

    fun generateFakeLessonPage(
        title: String = "lesson Page Title",
        markdown: String = "*Markdown*",
    ): LessonPage = LessonPage.InfoPage(title, markdown)

    fun generateFakeLesson(
        pagesCount: Int = 1,
        lessonData: LessonData = generateFakeLessonData(pagesCount = pagesCount),
        completed: Boolean = false,
    ): Lesson = Lesson(
        lessonData, completed
    )
  }
}