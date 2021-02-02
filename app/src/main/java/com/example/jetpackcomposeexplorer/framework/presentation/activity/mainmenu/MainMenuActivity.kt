package com.example.jetpackcomposeexplorer.framework.presentation.activity.mainmenu

import android.app.Activity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import dagger.hilt.android.AndroidEntryPoint


class MainMenuActivity : Activity() {
  lateinit var viewModel: MainMenuViewModel

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

//    viewModel = ViewModelProvider(this).get(MainMenuViewModel::class.java)
//    showMainFragment()
  }
}
