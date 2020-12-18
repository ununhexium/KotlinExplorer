package com.example.jetpackcomposeexplorer.model

import androidx.lifecycle.ViewModel
import com.example.jetpackcomposeexplorer.repository.ExploreRepository

class ExploreViewModel : ViewModel() {
  val alias = ExploreRepository.getProfileAlias()

  fun setAlias(value: String) {
    ExploreRepository.setProfileAlias(value)
  }
}
