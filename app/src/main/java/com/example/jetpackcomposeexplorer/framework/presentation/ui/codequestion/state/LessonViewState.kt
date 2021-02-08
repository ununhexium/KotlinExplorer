package com.example.jetpackcomposeexplorer.framework.presentation.ui.codequestion.state

import com.example.jetpackcomposeexplorer.business.domain.Lesson

data class LessonViewState(
    var lesson: Lesson,
    var page: Int,
) {
  val currentPage
    get() = lesson.pages.drop(page).firstOrNull()

  val progress: Float
    get() = 1.0f * (page + 1) / lesson.pages.size
}
