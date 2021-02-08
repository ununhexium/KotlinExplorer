package com.example.jetpackcomposeexplorer.framework.presentation.chapterlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
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
import androidx.compose.ui.Modifier
import com.example.jetpackcomposeexplorer.business.interactor.ActiveElements
import com.example.jetpackcomposeexplorer.framework.presentation.components.frame.ChapterCardData
import com.example.jetpackcomposeexplorer.framework.presentation.components.frame.ChapterList
import com.example.jetpackcomposeexplorer.framework.presentation.components.frame.ExploreDrawer
import com.example.jetpackcomposeexplorer.framework.presentation.components.frame.LessonListItemData
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

    val view = ComposeView(requireContext())
    view.setContent {
      Content(viewModel, viewLifecycleOwner)
    }

    viewModel.loadChapters()

    return view
  }

  private fun restoreInstanceState(savedInstanceState: Bundle?) {
    savedInstanceState?.let { inState ->
      (inState[NOTE_LIST_STATE_BUNDLE_KEY] as ChapterListViewState?)?.let { viewState ->
        viewModel.setViewState(viewState)
      }
    }
  }

  @Composable
  fun Content(viewModel: ChapterListViewModel, owner: LifecycleOwner) {
    MaterialTheme {
      val scaffoldState = rememberScaffoldState()

      Scaffold(
          drawerContent = {
            ExploreDrawer(
//                nav = findNavController(),
                onSelection = {
                  scaffoldState.drawerState.close()
                },
            )
          },
          topBar = {
            Row(horizontalArrangement = Arrangement.SpaceAround) {
              Text("KotlinExplorer",
                  style = MaterialTheme.typography.h4,
                  color = MaterialTheme.colors.primary
              )
            }
          },
      ) {
        val state by viewModel.viewState.observeAsState()

        state?.chapters?.let { chapters ->
          ChapterList(
              chapters = chapters.map {
                ChapterCardData(
                    it.id,
                    it.title,
                    0f,
                    it.lessons.map { lesson ->
                      LessonListItemData(lesson.id, lesson.title, false)
                    }
                )
              },
              onPlay = { _, lessonId ->
                val action = ChapterListFragmentDirections
                    .actionChapterListFragmentToQuizFragment(lessonId)

                ActiveElements.activeLessonId = lessonId
                findNavController().navigate(action)
              }
          )
        }
      }
    }
  }
}
