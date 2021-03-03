package net.lab0.kotlinexplorer.framework.presentation.fragment.chapterlist

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddTask
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.unit.dp
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import net.lab0.kotlinexplorer.framework.presentation.composable.SmallHorizontalSpacer
import net.lab0.kotlinexplorer.framework.presentation.composable.chapter.ChapterCardData
import net.lab0.kotlinexplorer.framework.presentation.composable.chapter.ChapterList
import net.lab0.kotlinexplorer.framework.presentation.composable.chapter.LessonListItemData
import net.lab0.kotlinexplorer.framework.presentation.composable.frame.TopLevelScaffold
import net.lab0.kotlinexplorer.framework.presentation.fragment.chapterlist.state.ChapterListStateEvent
import net.lab0.kotlinexplorer.framework.presentation.fragment.chapterlist.state.ChapterListViewState
import net.lab0.kotlinexplorer.mvi.BaseFragment
import javax.inject.Inject

@FlowPreview
@ExperimentalCoroutinesApi
@AndroidEntryPoint
class ChapterListFragment
@Inject constructor(
    private val viewModelFactory: ViewModelProvider.Factory,
) : BaseFragment<ChapterListStateEvent, ChapterListViewState>() {

  override val viewModel: ChapterListViewModel by viewModels { viewModelFactory }

  override fun onCreateComposeView(view: ComposeView) {
    view.setContent { Content() }
    viewModel.loadLessonsInProgress()
  }

  @Composable
  fun Content() {
    MaterialTheme {
      val scaffoldState = rememberScaffoldState()

      TopLevelScaffold(
          title = "Lessons",
          scaffoldState = scaffoldState,
          onProfileSelected = {
            findNavController().navigate(
                ChapterListFragmentDirections.actionChapterListFragmentToProfileGraph()
            )
          },
          onLessonsSelected = {
            // stay here
          }
      ) {
        val state by viewModel.uiDataState.collectAsState()
        val scrollState = rememberScrollState()

        Column(
            modifier = Modifier.verticalScroll(scrollState)
        ) {
          ChapterList(
              modifier = Modifier.padding(bottom = 64.dp),
              chapters = state.chapters.map { chapter ->
                val completion = state.getChapterCompletion(chapter)
                val lessonsInProgress = state.lessonsInProgress.map { it.lessonId }
                val nextLesson = chapter.lessons.firstOrNull { it.id !in lessonsInProgress }

                ChapterCardData(
                    chapter.id,
                    chapter.title,
                    completion,
                    chapter.lessons.map { lesson ->
                      LessonListItemData(
                          id = lesson.id,
                          title = lesson.title,
                          completed = lesson.id in lessonsInProgress,
                          lesson == nextLesson,
                          progress = state.getLessonCompletion(lesson.id)
                      )
                    }
                )
              },
              onPlay = { _, lessonId ->
                findNavController().navigate(
                    ChapterListFragmentDirections
                        .actionChapterListFragmentToLessonGraph(lessonId)
                )
              }
          )

          Row(
              modifier = Modifier.fillMaxWidth(),
              horizontalArrangement = Arrangement.SpaceAround
          ) {
            Button(
                onClick = { viewModel.requestMoreContent(requireContext()) },
                enabled = state.canRequestMoreChapters,
            ) {
              if (state.canRequestMoreChapters) {
                Icon(
                    imageVector = Icons.Default.AddTask,
                    "Add Task",
                )
                SmallHorizontalSpacer()
              }
              Text(text = if (state.canRequestMoreChapters) "More please!" else "Requested")
            }
          }
        }
      }
    }
  }
}
