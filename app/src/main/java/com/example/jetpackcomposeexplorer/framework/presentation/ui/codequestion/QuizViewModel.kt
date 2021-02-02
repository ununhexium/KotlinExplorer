package com.example.jetpackcomposeexplorer.framework.presentation.ui.codequestion

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jetpackcomposeexplorer.business.course.implementation.LessonPage
import com.example.jetpackcomposeexplorer.business.domain.Exercise
import com.example.jetpackcomposeexplorer.framework.datasource.service.LessonDaoService
import kotlinx.coroutines.launch

class QuizViewModel(val exercise:Exercise, private val lessonDaoService: LessonDaoService) : ViewModel() {
  private val pages = exercise.lessonData.pages

  val page: MutableState<LessonPage?> = mutableStateOf(pages.first())
  val progress = mutableStateOf(
      pages.indexOf(page.value) / pages.size.toFloat()
  )

  private val isLastPage
    get() = page.value == null

  fun goToNextPage() {
    page.value = pages.dropWhile { it != page.value }.drop(1).firstOrNull()
    if (isLastPage) {
      viewModelScope.launch {
        lessonDaoService.markAsDone(exercise)
      }
    }
  }
}
