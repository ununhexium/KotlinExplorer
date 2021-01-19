package com.example.jetpackcomposeexplorer.model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.jetpackcomposeexplorer.repository.ExploreRepository

class ExploreViewModel : ViewModel() {
  val alias = MutableLiveData(ExploreRepository.getProfileAlias())

  fun setAlias(value: String) {
    alias.postValue(value)
    ExploreRepository.setProfileAlias(value)
  }
}
