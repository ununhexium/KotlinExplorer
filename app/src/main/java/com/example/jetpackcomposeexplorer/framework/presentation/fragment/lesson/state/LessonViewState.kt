package com.example.jetpackcomposeexplorer.framework.presentation.fragment.lesson.state

import com.example.jetpackcomposeexplorer.business.domain.Chapter
import com.example.jetpackcomposeexplorer.business.domain.Lesson
import com.example.jetpackcomposeexplorer.business.domain.LessonBrowser

data class LessonViewState(
    var lesson: Lesson,
    var page: Int,
    val answers: List<CodeAnswerState> = listOf(),
) {

  val currentPage =
      lesson.pages.drop(page).firstOrNull()

  val progress =
      1.0f * (page + 1) / lesson.pages.size

  fun answersCount(state: CodeAnswerState) =
      answers.count { it == state }

  val chapter =
      LessonBrowser.getChapterForLesson(lesson.id)
}
