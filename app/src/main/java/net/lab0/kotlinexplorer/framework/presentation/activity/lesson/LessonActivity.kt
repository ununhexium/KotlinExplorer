package net.lab0.kotlinexplorer.framework.presentation.activity.lesson

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
class LessonActivity : AppCompatActivity() {
  @Inject
  lateinit var fragmentFactory: KotlinExplorerFragmentFactory

//  private val lessonViewModel: LessonViewModel by viewModels()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_lesson)
  }
}
