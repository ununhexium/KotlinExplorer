package net.lab0.kotlinexplorer.framework.presentation.fragment.lessonpage

import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.viewModels
import net.lab0.kotlinexplorer.framework.presentation.fragment.lessonpage.mvi.LessonPageUiEvent
import net.lab0.kotlinexplorer.framework.presentation.fragment.lessonpage.mvi.LessonPageUiState
import net.lab0.kotlinexplorer.mvi.BaseFragment

class LessonPageFragment() : BaseFragment<LessonPageUiEvent, LessonPageUiState>() {
  override val viewModel: LessonPageViewModel by viewModels()

  override fun onCreateComposeView(view: ComposeView) {
    view.setContent {
      val collected = viewModel.uiDataState.collectAsState()
      val state = collected.value

      Scaffold(
          drawerContent = {
          }
      ) {
        Text("LessonPage")
      }
    }
  }
}