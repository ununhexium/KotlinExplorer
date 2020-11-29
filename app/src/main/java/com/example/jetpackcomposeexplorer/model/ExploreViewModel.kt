package com.example.jetpackcomposeexplorer.model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class ExploreViewModel : ViewModel() {
  var page by mutableStateOf(homePage)
    private set

  fun goToPage(pageId: String) {
    page = pageId
  }
}