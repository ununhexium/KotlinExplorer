package net.lab0.kotlinexplorer.framework.presentation.activity.lesson

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.material.Scaffold
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import net.lab0.kotlinexplorer.business.domain.LessonBrowser
import net.lab0.kotlinexplorer.business.domain.LessonPage
import net.lab0.kotlinexplorer.framework.presentation.activity.lesson.mvi.LessonViewModel
import net.lab0.kotlinexplorer.framework.presentation.composable.lesson.InfoLessonPage
import net.lab0.kotlinexplorer.framework.presentation.composable.lesson.LessonDrawer
import net.lab0.kotlinexplorer.framework.presentation.composable.lesson.LessonPage
import net.lab0.kotlinexplorer.utils.Do

class InfoPageFragment(
    private val viewModelFactory: ViewModelProvider.Factory,
) : Fragment() {
  private val args: InfoPageFragmentArgs by navArgs()
  private val activityViewModel: LessonViewModel by activityViewModels { viewModelFactory }

  override fun onCreateView(
      inflater: LayoutInflater,
      container: ViewGroup?,
      savedInstanceState: Bundle?
  ): View {
    return ComposeView(requireContext()).also {
      val lesson = LessonBrowser.getLessonById(args.lessonId)
      val page = lesson.pages[args.page] as LessonPage.InfoPage
      val chapter = LessonBrowser.getChapterForLesson(args.lessonId)!!
      it.setContent {
        Scaffold(
            drawerContent = {
              LessonDrawer(
                  chapter = chapter.title,
                  lesson = lesson.title,
                  lessonPages = chapter.lessons.map { it.title },
                  currentPage = lesson.title
              )
            }
        ) {
          LessonPage(
              progress = 1f * args.page / lesson.pages.size,
              title = lesson.title,
              onBack = {
                findNavController().navigate(
                    InfoPageFragmentDirections
                        .actionLessonInfoPageFragmentToChapterListFragment()
                )
              }
          ) {
            InfoLessonPage(
                markdownAsString = page.markdown,
                nextPage = {
                  activityViewModel.countMark(page, CodeAnswerState.NEUTRAL)
                  val nextPageIndex = args.page + 1
                  val maybeNextPage = lesson.pages.getOrNull(nextPageIndex)
                  Do exhaustive when (maybeNextPage) {
                    null -> {
                      activityViewModel.saveLesson()
                      findNavController().navigate(
                          InfoPageFragmentDirections
                              .actionInfoPageFragmentToLessonFeedbackFragment(args.lessonId)
                      )
                    }
                    is LessonPage.InfoPage ->
                      findNavController().navigate(
                          InfoPageFragmentDirections
                              .actionInfoPageFragmentSelf(args.lessonId, nextPageIndex)
                      )

                    is LessonPage.CodeQuestionPage ->
                      findNavController().navigate(
                          InfoPageFragmentDirections
                              .actionInfoPageFragmentToCodeQuestionPageFragment(
                                  args.lessonId,
                                  nextPageIndex
                              )
                      )

                    is LessonPage.MultipleChoice -> TODO("Multiple choice page")
                  }
                }
            )
          }
        }
      }
    }
  }
}
