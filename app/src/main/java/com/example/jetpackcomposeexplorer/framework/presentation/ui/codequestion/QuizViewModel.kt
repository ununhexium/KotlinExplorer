package com.example.jetpackcomposeexplorer.framework.presentation.ui.codequestion

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.jetpackcomposeexplorer.business.course.LessonPage

class QuizViewModel<P : LessonPage>(private val pages: List<P>) : ViewModel() {
  val page: MutableState<P?> = mutableStateOf(pages.first())
  val progress = mutableStateOf(pages.indexOf(page.value) / pages.size.toFloat())

  fun goToNextPage() {
    page.value = pages.dropWhile { it != page.value }.drop(1).firstOrNull()
  }
}
