package net.lab0.kotlinexplorer.framework.ui

import android.content.Context
import androidx.navigation.fragment.NavHostFragment
import net.lab0.kotlinexplorer.framework.presentation.common.JetpackExplorerFragmentFactory
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
