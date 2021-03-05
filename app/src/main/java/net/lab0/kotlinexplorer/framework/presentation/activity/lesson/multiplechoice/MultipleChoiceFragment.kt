package net.lab0.kotlinexplorer.framework.presentation.activity.lesson.multiplechoice

import android.os.Bundle
import androidx.compose.material.Scaffold
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import kotlinx.coroutines.ExperimentalCoroutinesApi
import net.lab0.kotlinexplorer.business.domain.LessonBrowser
import net.lab0.kotlinexplorer.business.domain.LessonPage
import net.lab0.kotlinexplorer.framework.presentation.activity.lesson.AnswerCorrectness.FAILURE
import net.lab0.kotlinexplorer.framework.presentation.activity.lesson.AnswerCorrectness.SUCCESS
import net.lab0.kotlinexplorer.framework.presentation.activity.lesson.NextPageSelectorMixin
import net.lab0.kotlinexplorer.framework.presentation.activity.lesson.multiplechoice.mvi.MultipleChoiceUiEvent
import net.lab0.kotlinexplorer.framework.presentation.activity.lesson.multiplechoice.mvi.MultipleChoiceUiState
import net.lab0.kotlinexplorer.framework.presentation.activity.lesson.mvi.LessonViewModel
import net.lab0.kotlinexplorer.framework.presentation.composable.lesson.LessonDrawer
import net.lab0.kotlinexplorer.framework.presentation.composable.lesson.LessonPage
import net.lab0.kotlinexplorer.framework.presentation.composable.lesson.MultipleChoicePage
import net.lab0.kotlinexplorer.framework.ui.theme.KotlinExplorerTheme
import net.lab0.kotlinexplorer.mvi.BaseFragment

@ExperimentalCoroutinesApi
class MultipleChoiceFragment(
  private val viewModelFactory: ViewModelProvider.Factory,
) : BaseFragment<MultipleChoiceUiEvent, MultipleChoiceUiState>(), NextPageSelectorMixin {
  private val args: MultipleChoiceFragmentArgs by navArgs()
  private val activityViewModel: LessonViewModel by activityViewModels { viewModelFactory }
  override val viewModel: MultipleChoiceViewModel by viewModels()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    val lesson = LessonBrowser.getLessonById(args.lessonId)
    val chapter = LessonBrowser.getChapterForLesson(args.lessonId)!!
    viewModel.init(args.page, lesson.pages[args.page] as LessonPage.MultipleChoice, chapter)
  }

  override fun onCreateComposeView(view: ComposeView) {
    view.setContent {
      val collected = viewModel.uiDataState.collectAsState()
      val state = collected.value

      val lesson = LessonBrowser.getLessonById(args.lessonId)
      val page = lesson.pages[args.page] as LessonPage.MultipleChoice
      val chapter = LessonBrowser.getChapterForLesson(args.lessonId)!!

      KotlinExplorerTheme {
        Scaffold(
          drawerContent = {
            LessonDrawer(
              chapter = chapter.title,
              lesson = lesson.title,
              lessonPages = lesson.pages.map { it.title },
              currentPage = page.title
            ) { title ->
              onNextPage(state, lesson.pages.indexOfFirst { it.title == title })()
            }
          }
        ) {
          LessonPage(
            lessonId = args.lessonId,
            progress = state.progress,
            title = state.lessonPage.title,
            onBack = {
              findNavController().navigate(
                MultipleChoiceFragmentDirections
                  .actionMultipleChoicePageFragmentToChapterListFragment()
              )
            },
            onProblemReport = {
              activityViewModel.onProblemReport(it, requireContext())
            }
          ) {
            MultipleChoicePage(
              model = viewModel,
              state = state,
              onNextPage = onNextPage(state)
            )
          }
        }
      }
    }
  }

  private fun onNextPage(state: MultipleChoiceUiState, targetPage: Int = args.page + 1) =
    nextPage(
      activityViewModel = activityViewModel,
      correctness = if (state.isCorrectAnswer) SUCCESS else FAILURE,
      page = args.page,
      nextPage = targetPage,
      lessonId = args.lessonId,
      navController = findNavController(),
      navigationToFeedback = MultipleChoiceFragmentDirections::actionMultipleChoicePageFragmentToLessonFeedbackFragment,
      navigationToNextChapter = MultipleChoiceFragmentDirections::actionMultipleChoicePageFragmentToNextLessonFragment,
      navigationToInfo = MultipleChoiceFragmentDirections::actionMultipleChoicePageFragmentToInfoPageFragment,
      navigationToCodeQuestion = MultipleChoiceFragmentDirections::actionMultipleChoicePageFragmentToCodeQuestionPageFragment,
      navigationToMultipleChoice = MultipleChoiceFragmentDirections::actionMultipleChoicePageFragmentSelf,
    )
}
