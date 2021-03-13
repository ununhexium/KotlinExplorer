package net.lab0.kotlinexplorer.framework.presentation.activity.welcome.home

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import net.lab0.kotlinexplorer.framework.presentation.common.JetpackExplorerFragmentFactory
import net.lab0.kotlinexplorer.framework.presentation.common.JetpackExplorerViewModelFactory
import net.lab0.kotlinexplorer.framework.presentation.composable.frame.HomeUi
import net.lab0.kotlinexplorer.framework.ui.theme.KotlinExplorerTheme
import javax.inject.Inject


@FlowPreview
@ExperimentalCoroutinesApi
@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {
  @Inject
  lateinit var fragmentFactory: JetpackExplorerFragmentFactory

  @Inject
  lateinit var viewModelFactory: JetpackExplorerViewModelFactory

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      KotlinExplorerTheme {
        HomeUi(viewModelFactory)
      }
    }
  }
}
