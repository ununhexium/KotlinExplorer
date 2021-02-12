package com.example.jetpackcomposeexplorer.framework.presentation.fragment.chapterlist

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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.jetpackcomposeexplorer.framework.presentation.fragment.chapterlist.state.ChapterListStateEvent
import com.example.jetpackcomposeexplorer.framework.presentation.fragment.chapterlist.state.ChapterListViewState
import com.example.jetpackcomposeexplorer.framework.presentation.components.frame.ChapterCardData
import com.example.jetpackcomposeexplorer.framework.presentation.components.frame.ChapterList
import com.example.jetpackcomposeexplorer.framework.presentation.components.frame.ExploreDrawer
import com.example.jetpackcomposeexplorer.framework.presentation.components.frame.LessonListItemData
import com.example.jetpackcomposeexplorer.mvi.BaseFragment
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
) : BaseFragment<ChapterListStateEvent, ChapterListViewState>() {

  private val NOTE_LIST_STATE_BUNDLE_KEY =
      "com.example.jetpackcomposeexplorer.framework.presentation.fragment.chapterlist"

  override val viewModel: ChapterListViewModel by viewModels { viewModelFactory }

  override fun onCreateView(
      inflater: LayoutInflater,
      container: ViewGroup?,
      savedInstanceState: Bundle?,
  ): View {
    val view = ComposeView(requireContext())

    view.setContent { Content() }
    viewModel.loadLessonsInProgress()

    return view
  }

  @Composable
  fun Content() {
    MaterialTheme {
      val scaffoldState = rememberScaffoldState()

      Scaffold(
          drawerContent = {
            ExploreDrawer(
                onSelection = {
                  scaffoldState.drawerState.close()
                },
            )
          },
          topBar = {
            Row(horizontalArrangement = Arrangement.SpaceAround) {
              Text(
                  "KotlinExplorer",
                  style = MaterialTheme.typography.h4,
                  color = MaterialTheme.colors.primary
              )
            }
          },
      ) {
        val state by viewModel.uiDataState.collectAsState()

        ChapterList(
            chapters = state.chapters.map { chapter ->
              val completion = state.getChapterCompletion(chapter)

              ChapterCardData(
                  chapter.id,
                  chapter.title,
                  completion,
                  chapter.lessons.map { lesson ->
                    LessonListItemData(
                        lesson.id,
                        lesson.title,
                        lesson.id in state.lessonsInProgress.map { it.lessonId }
                    )
                  }
              )
            },
            onPlay = { _, lessonId ->
              val action = ChapterListFragmentDirections
                  .actionChapterListFragmentToLessonFragment(lessonId)

              findNavController().navigate(action)
            }
        )
      }
    }
  }
}