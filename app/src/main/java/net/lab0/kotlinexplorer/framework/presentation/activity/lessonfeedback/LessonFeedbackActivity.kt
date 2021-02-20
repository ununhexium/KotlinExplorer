package net.lab0.kotlinexplorer.framework.presentation.activity.lessonfeedback

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import net.lab0.kotlinexplorer.R

@FlowPreview
@ExperimentalCoroutinesApi
@AndroidEntryPoint
class LessonFeedbackActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_user_profile)
  }
}
