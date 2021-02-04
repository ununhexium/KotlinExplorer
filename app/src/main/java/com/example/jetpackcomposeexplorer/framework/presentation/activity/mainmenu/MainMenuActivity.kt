package com.example.jetpackcomposeexplorer.framework.presentation.activity.mainmenu

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainMenuActivity : AppCompatActivity() {
  lateinit var viewModel: MainMenuViewModel

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

//    viewModel = ViewModelProvider(this).get(MainMenuViewModel::class.java)
//    showMainFragment()
  }
}
