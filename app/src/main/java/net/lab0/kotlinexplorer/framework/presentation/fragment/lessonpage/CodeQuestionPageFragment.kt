package net.lab0.kotlinexplorer.framework.presentation.fragment.lessonpage

import android.os.Bundle
import androidx.compose.material.Scaffold
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import kotlinx.coroutines.ExperimentalCoroutinesApi
import net.lab0.kotlinexplorer.business.domain.LessonBrowser
import net.lab0.kotlinexplorer.business.domain.LessonPage
import net.lab0.kotlinexplorer.framework.presentation.composable.code.CodeQuizPage2
import net.lab0.kotlinexplorer.framework.presentation.composable.lesson.LessonDrawer
import net.lab0.kotlinexplorer.framework.presentation.composable.lesson.LessonPage
import net.lab0.kotlinexplorer.framework.presentation.fragment.lessonfirstpage.LessonFirstPageFragmentDirections
import net.lab0.kotlinexplorer.framework.presentation.fragment.lessonpage.mvi.LessonPageUiEvent
import net.lab0.kotlinexplorer.framework.presentation.fragment.lessonpage.mvi.LessonPageUiState
import net.lab0.kotlinexplorer.mvi.BaseFragment
import net.lab0.kotlinexplorer.utils.Do

@ExperimentalCoroutinesApi
class CodeQuestionPageFragment : BaseFragment<LessonPageUiEvent, LessonPageUiState>() {
  private val args: CodeQuestionPageFragmentArgs by navArgs()
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
                      .actionLessonPageFragmentPop()
              )
            }
        ) {
          CodeQuizPage2(
              model = viewModel,
              nextQuestion = {
                val nextPageIndex = args.page + 1
                val maybeNextPage = LessonBrowser
                    .getLessonById(args.lessonId)
                    .pages
                    .getOrNull(nextPageIndex)

                Do exhaustive when (maybeNextPage) {
                  is LessonPage.InfoPage ->
                    findNavController().navigate(
                        CodeQuestionPageFragmentDirections
                            .actionLessonPageFragmentToLessonInfoPageFragment(args.lessonId, nextPageIndex)
                    )

                  is LessonPage.CodeQuestionPage ->
                    findNavController().navigate(
                        CodeQuestionPageFragmentDirections.actionLessonPageFragmentSelf(
                            args.lessonId,
                            nextPageIndex
                        )
                    )
                  is LessonPage.MultipleChoice -> TODO()
                  null -> TODO("count correct answers")
//                  viewModel.nextPage(
//                      if (model.isCorrectAnswer()) CodeAnswerState.SUCCESS
//                      else CodeAnswerState.FAILURE
//                  )
                }
              },
              onSelect = { viewModel.select(it.id) },
          )
        }
      }
    }
  }
}
