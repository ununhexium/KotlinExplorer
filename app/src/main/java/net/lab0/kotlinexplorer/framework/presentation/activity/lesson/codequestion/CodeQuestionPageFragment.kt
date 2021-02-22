package net.lab0.kotlinexplorer.framework.presentation.activity.lesson.codequestion

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
import net.lab0.kotlinexplorer.framework.presentation.activity.lesson.CodeAnswerState
import net.lab0.kotlinexplorer.framework.presentation.activity.lesson.codequestion.mvi.LessonPageUiEvent
import net.lab0.kotlinexplorer.framework.presentation.activity.lesson.codequestion.mvi.LessonPageUiState
import net.lab0.kotlinexplorer.framework.presentation.activity.lesson.mvi.LessonViewModel
import net.lab0.kotlinexplorer.framework.presentation.composable.code.CodeQuizPage2
import net.lab0.kotlinexplorer.framework.presentation.composable.lesson.LessonDrawer
import net.lab0.kotlinexplorer.framework.presentation.composable.lesson.LessonPage
import net.lab0.kotlinexplorer.mvi.BaseFragment
import net.lab0.kotlinexplorer.utils.Do

@ExperimentalCoroutinesApi
class CodeQuestionPageFragment(
    private val viewModelFactory: ViewModelProvider.Factory,
) : BaseFragment<LessonPageUiEvent, LessonPageUiState>() {
  private val args: CodeQuestionPageFragmentArgs by navArgs()
  val activityViewModel: LessonViewModel by activityViewModels { viewModelFactory }
  override val viewModel: CodeQuestionPageFragmentViewModel by viewModels()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    val lesson = LessonBrowser.getLessonById(args.lessonId)
    val chapter = LessonBrowser.getChapterForLesson(args.lessonId)!!
    viewModel.init(args.page, lesson.pages[args.page] as LessonPage.CodeQuestionPage, chapter)
  }

  override fun onCreateComposeView(view: ComposeView) {
    view.setContent {
      val collected = viewModel.uiDataState.collectAsState()
      val state = collected.value

      Scaffold(
          drawerContent = {
            LessonDrawer(
                chapter = state.chapter.title,
                lesson = state.lessonPage.title,
                lessonPages = state.chapter.lessons.map { it.title },
                currentPage = state.lessonPage.title
            )
          }
      ) {
        LessonPage(
            progress = state.progress,
            title = state.lessonPage.title,
            onBack = {
              findNavController().navigate(
                  CodeQuestionPageFragmentDirections
                      .actionCodeQuestionPageFragmentToChapterListFragment()
              )
            }
        ) {
          CodeQuizPage2(
              model = viewModel,
              nextQuestion = {
                activityViewModel.countMark(
                    lessonPage = viewModel.uiDataState.value.lessonPage,
                    correctness = if (viewModel.uiDataState.value.isCorrectAnswer) {
                      CodeAnswerState.SUCCESS
                    } else {
                      CodeAnswerState.FAILURE
                    }
                )

                val nextPageIndex = args.page + 1
                val maybeNextPage = LessonBrowser.getLessonById(args.lessonId)
                    .pages
                    .getOrNull(nextPageIndex)

                // TODO: extract this logic and put it in a class that is common to the 3 page kinds?
                Do exhaustive when (maybeNextPage) {
                  // no more pages
                  null -> {
                    activityViewModel.saveLesson()
                    findNavController().navigate(
                        CodeQuestionPageFragmentDirections
                            .actionCodeQuestionPageFragmentToLessonFeedbackFragment(args.lessonId)
                    )
                  }

                  is LessonPage.InfoPage ->
                    findNavController().navigate(
                        CodeQuestionPageFragmentDirections
                            .actionCodeQuestionPageFragmentToInfoPageFragment(
                                args.lessonId,
                                nextPageIndex
                            )
                    )

                  is LessonPage.CodeQuestionPage ->
                    findNavController().navigate(
                        CodeQuestionPageFragmentDirections.actionLessonCodeQuestionPageFragmentSelf(
                            args.lessonId,
                            nextPageIndex
                        )
                    )

                  is LessonPage.MultipleChoice -> TODO()
                }
              },
              onSelect = { viewModel.select(it.id) },
          )
        }
      }
    }
  }
}