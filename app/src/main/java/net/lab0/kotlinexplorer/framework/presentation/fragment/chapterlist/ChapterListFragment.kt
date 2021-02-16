package net.lab0.kotlinexplorer.framework.presentation.fragment.chapterlist

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts.StartActivityForResult
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.unit.dp
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.firebase.ui.auth.AuthUI
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import net.lab0.kotlinexplorer.framework.presentation.components.frame.ChapterCardData
import net.lab0.kotlinexplorer.framework.presentation.components.frame.ChapterList
import net.lab0.kotlinexplorer.framework.presentation.components.frame.ExploreDrawer
import net.lab0.kotlinexplorer.framework.presentation.components.frame.LessonListItemData
import net.lab0.kotlinexplorer.framework.presentation.fragment.chapterlist.state.ChapterListStateEvent
import net.lab0.kotlinexplorer.framework.presentation.fragment.chapterlist.state.ChapterListViewState
import net.lab0.kotlinexplorer.framework.presentation.intent.requestSignIn
import net.lab0.kotlinexplorer.mvi.BaseFragment
import javax.inject.Inject

@FlowPreview
@ExperimentalCoroutinesApi
@AndroidEntryPoint
class ChapterListFragment
@Inject constructor(
    private val viewModelFactory: ViewModelProvider.Factory,
) : BaseFragment<ChapterListStateEvent, ChapterListViewState>() {

  private val NOTE_LIST_STATE_BUNDLE_KEY =
      "net.lab0.kotlinexplorer.framework.presentation.fragment.chapterlist"

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
          scaffoldState = scaffoldState,
          drawerContent = {
            ExploreDrawer(
                onProfile = {
                  scaffoldState.drawerState.close()
//                  requestSignIn()
                  findNavController().navigate(
                      ChapterListFragmentDirections.actionChapterListFragmentToUserProfile()
                  )
                },
            )
          },
          topBar = {
            TopAppBar(
                title = {
                  Text(
                      text = "Kotlin Explorer",
                      style = MaterialTheme.typography.h4,
                  )
                },
                navigationIcon = {
                  IconButton(
                      onClick = {
                        scaffoldState.drawerState.open()
                      }
                  ) {
                    Icon(Icons.Default.Menu)
                  }
                },
                elevation = 4.dp,
            )
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
                        lesson.id in state.lessonsInProgress.map { it.lessonId },
                        state.getLessonCompletion(lesson.id)
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

  companion object {
    private const val RC_SIGN_IN = 123
  }
}
