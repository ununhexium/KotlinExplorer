package net.lab0.kotlinexplorer.framework.presentation.activity.welcome.home

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import net.lab0.kotlinexplorer.framework.presentation.common.KotlinExplorerFragmentFactory
import net.lab0.kotlinexplorer.framework.presentation.common.KotlinExplorerViewModelFactory
import net.lab0.kotlinexplorer.framework.presentation.composable.frame.HomeNav
import net.lab0.kotlinexplorer.framework.ui.theme.KotlinExplorerTheme
import javax.inject.Inject


@FlowPreview
@ExperimentalCoroutinesApi
@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {
  @Inject
  lateinit var fragmentFactory: KotlinExplorerFragmentFactory

  @Inject
  lateinit var viewModelFactory: KotlinExplorerViewModelFactory

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      KotlinExplorerTheme {
        HomeNav(viewModelFactory)
      }
    }
  }
}
