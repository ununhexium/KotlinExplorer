package com.example.jetpackcomposeexplorer.framework.presentation.chapterlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.material.Text
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.example.jetpackcomposeexplorer.framework.presentation.chapterlist.state.ChapterListViewState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import javax.inject.Inject

@FlowPreview
@ExperimentalCoroutinesApi
@AndroidEntryPoint
class ChapterListFragment
@Inject constructor(
    private val viewModelFactory: ViewModelProvider.Factory,
) : Fragment() {

  val NOTE_LIST_STATE_BUNDLE_KEY =
      "com.example.jetpackcomposeexplorer.framework.presentation.chapterlist"

  private val viewModel: ChapterListViewModel by viewModels {
    viewModelFactory
  }

  override fun onCreateView(
      inflater: LayoutInflater,
      container: ViewGroup?,
      savedInstanceState: Bundle?,
  ): View {

    restoreInstanceState(savedInstanceState)

    return ComposeView(requireContext()).apply {
      viewModel.viewState.observe(viewLifecycleOwner) { state ->
        setContent {
          Text(text = state.chapters?.size.toString())
        }
      }
    }
  }

  private fun restoreInstanceState(savedInstanceState: Bundle?) {
    savedInstanceState?.let { inState ->
      (inState[NOTE_LIST_STATE_BUNDLE_KEY] as ChapterListViewState?)?.let { viewState ->
        viewModel.setViewState(viewState)
      }
    }
  }
}
