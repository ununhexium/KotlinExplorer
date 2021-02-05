package com.example.jetpackcomposeexplorer.framework.ui

import android.content.Context
import androidx.navigation.fragment.NavHostFragment
import com.example.jetpackcomposeexplorer.framework.presentation.common.JetpackExplorerFragmentFactory
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import javax.inject.Inject

@FlowPreview
@ExperimentalCoroutinesApi
@AndroidEntryPoint
class MainNavHostFragment : NavHostFragment() {

  @Inject
  lateinit var fragmentFactory: JetpackExplorerFragmentFactory

  override fun onAttach(context: Context) {
    super.onAttach(context)
    childFragmentManager.fragmentFactory = fragmentFactory
  }
}
