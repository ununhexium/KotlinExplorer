package com.example.jetpackcomposeexplorer

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.jetpackcomposeexplorer.framework.presentation.ui.codequestion.QuizFragmentFactory
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

  @Inject
  lateinit var fragmentFactory: QuizFragmentFactory

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    supportFragmentManager.fragmentFactory = fragmentFactory
//    supportFragmentManager.beginTransaction()
//        .replace(R.id.main_container, HomeFragment::class.java, null)
//        .commit()
  }
}
