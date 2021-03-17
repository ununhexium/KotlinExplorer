package net.lab0.kotlinexplorer.framework.ui

import android.content.Context
import androidx.navigation.fragment.NavHostFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import net.lab0.kotlinexplorer.framework.presentation.common.KotlinExplorerFragmentFactory
import javax.inject.Inject

@FlowPreview
@ExperimentalCoroutinesApi
@AndroidEntryPoint
class MainNavHostFragment : NavHostFragment() {

  @Inject
  lateinit var fragmentFactory: KotlinExplorerFragmentFactory

  override fun onAttach(context: Context) {
    super.onAttach(context)
    childFragmentManager.fragmentFactory = fragmentFactory
  }
}
