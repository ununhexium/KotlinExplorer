package com.example.jetpackcomposeexplorer.framework.presentation.chapterlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import com.example.jetpackcomposeexplorer.framework.presentation.chapterlist.state.ChapterListViewState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import javax.inject.Inject
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import com.example.jetpackcomposeexplorer.utils.printLogD

@FlowPreview
@ExperimentalCoroutinesApi
@AndroidEntryPoint
class ChapterListFragment
@Inject constructor(
    private val viewModelFactory: ViewModelProvider.Factory,
) : Fragment() {

  private val NOTE_LIST_STATE_BUNDLE_KEY =
      "com.example.jetpackcomposeexplorer.framework.presentation.chapterlist"

  private val viewModel: ChapterListViewModel by viewModels {
    viewModelFactory
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    viewModel.setupChannel()
  }

  override fun onCreateView(
      inflater: LayoutInflater,
      container: ViewGroup?,
      savedInstanceState: Bundle?,
  ): View {

    restoreInstanceState(savedInstanceState)

    viewModel.viewState.observe(viewLifecycleOwner) {
      printLogD(this::class.simpleName, "Updated State $it")
    }

    return ComposeView(requireContext()).apply {
      setContent {
        Test(viewModel, viewLifecycleOwner)
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

@FlowPreview
@ExperimentalCoroutinesApi
@Composable
fun Test(viewModel: ChapterListViewModel, owner: LifecycleOwner) {
  MaterialTheme {
    Scaffold {
      val state by viewModel.viewState.observeAsState()

      Column {
        if(state == null) {
          Text("No state")
        }
        if(state?.chapters == null) {
          Text("No chapters")
        }
        Text(text = state?.chapters?.size.toString())

        Button(onClick = { viewModel.loadChapters() }) {
          Text("Update")
        }
      }
    }
  }
}
