package com.example.jetpackcomposeexplorer.framework.presentation.ui.codequestion

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jetpackcomposeexplorer.business.course.LessonPage
import com.example.jetpackcomposeexplorer.business.domain.Lesson
import com.example.jetpackcomposeexplorer.framework.datasource.service.LessonDaoService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class QuizViewModel(private val lesson: Lesson, private val lessonDaoService: LessonDaoService) : ViewModel() {
  private val pages = lesson.lessonData.pages

  val page: MutableState<LessonPage?> = mutableStateOf(pages.first())
  val progress = mutableStateOf(
      pages.indexOf(page.value) / pages.size.toFloat()
  )

  private val isLastPage
    get() = page.value == null

  fun goToNextPage() {
    page.value = pages.dropWhile { it != page.value }.drop(1).firstOrNull()
    if(isLastPage) {
      viewModelScope.launch {
        lessonDaoService.markAsDone(lesson)
      }
    }
  }
}
