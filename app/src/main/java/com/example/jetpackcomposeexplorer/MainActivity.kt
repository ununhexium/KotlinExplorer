package com.example.jetpackcomposeexplorer

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.jetpackcomposeexplorer.framework.presentation.common.JetpackExplorerFragmentFactory
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import javax.inject.Inject

@FlowPreview
@ExperimentalCoroutinesApi
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

  @Inject
  lateinit var fragmentFactory: JetpackExplorerFragmentFactory

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    setFragmentFactory()

    setContentView(R.layout.activity_main)
  }

  private fun setFragmentFactory() {
    supportFragmentManager.fragmentFactory = fragmentFactory
  }
}
