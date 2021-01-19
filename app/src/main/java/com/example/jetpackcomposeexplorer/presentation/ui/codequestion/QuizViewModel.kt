package com.example.jetpackcomposeexplorer.presentation.ui.codequestion

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class QuizViewModel(private val pages: List<CodeQuestionPage>) : ViewModel() {
  val page: MutableState<CodeQuestionPage?> = mutableStateOf(pages.first())
  val progress = mutableStateOf(pages.indexOf(page.value) / pages.size.toFloat())

  fun goToNextPage() {
    page.value = pages.dropWhile { it != page.value }.drop(1).firstOrNull()
  }
}
