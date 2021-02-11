package com.example.jetpackcomposeexplorer.framework.presentation.fragment.lesson.state

import com.example.jetpackcomposeexplorer.business.domain.Lesson

data class LessonViewState(
    var lesson: Lesson,
    var page: Int,
    val answers: List<CodeAnswerState> = listOf(),
) {
  val currentPage
    get() = lesson.pages.drop(page).firstOrNull()

  val progress: Float
    get() = 1.0f * (page + 1) / lesson.pages.size

  fun answersCount(state: CodeAnswerState) =
      answers.count { it == state }
}
