package net.lab0.kotlinexplorer.framework.presentation.activity.welcome.login

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import net.lab0.kotlinexplorer.R
import net.lab0.kotlinexplorer.framework.presentation.common.KotlinExplorerFragmentFactory
import javax.inject.Inject

@FlowPreview
@ExperimentalCoroutinesApi
@AndroidEntryPoint
class WelcomeActivity : AppCompatActivity() {

  @Inject
  lateinit var fragmentFactory: KotlinExplorerFragmentFactory

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_welcome)
  }

}
