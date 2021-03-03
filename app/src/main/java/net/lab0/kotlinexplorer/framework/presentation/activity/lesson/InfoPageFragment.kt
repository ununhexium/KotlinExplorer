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
import net.lab0.kotlinexplorer.framework.ui.theme.KotlinExplorerTheme

class InfoPageFragment(
    private val viewModelFactory: ViewModelProvider.Factory,
) : Fragment(), NextPageSelectorMixin {
  private val args: InfoPageFragmentArgs by navArgs()
  private val activityViewModel: LessonViewModel by activityViewModels { viewModelFactory }

  override fun onCreateView(
      inflater: LayoutInflater,
      container: ViewGroup?,
      savedInstanceState: Bundle?
  ): View {
    return ComposeView(requireContext()).also { it ->

      val lesson = LessonBrowser.getLessonById(args.lessonId)
      val page = lesson.pages[args.page] as LessonPage.InfoPage
      val chapter = LessonBrowser.getChapterForLesson(args.lessonId)!!

      it.setContent {
        KotlinExplorerTheme {
          Scaffold(
              drawerContent = {
                LessonDrawer(
                    chapter = chapter.title,
                    lesson = lesson.title,
                    lessonPages = lesson.pages.map { it.title },
                    currentPage = page.title
                )
              }
          ) {
            LessonPage(
                lessonId = args.lessonId,
                progress = 1f * args.page / lesson.pages.size,
                title = page.title,
                onBack = {
                  findNavController().navigate(
                      InfoPageFragmentDirections
                          .actionLessonInfoPageFragmentToChapterListFragment()
                  )
                },
                onProblemReport = {
                  activityViewModel.onProblemReport(it, this@InfoPageFragment.requireContext())
                }
            ) {
              InfoLessonPage(
                  markdownAsString = page.markdown,
                  nextPage = nextPage(
                      activityViewModel,
                      AnswerCorrectness.NEUTRAL,
                      args.page,
                      args.lessonId,
                      findNavController(),
                      navigationToFeedback = InfoPageFragmentDirections::actionInfoPageFragmentToLessonFeedbackFragment,
                      navigationToInfo = InfoPageFragmentDirections::actionInfoPageFragmentSelf,
                      navigationToMultipleChoice = InfoPageFragmentDirections::actionInfoPageFragmentToMultipleChoicePageFragment,
                      navigationToCodeQuestion = InfoPageFragmentDirections::actionInfoPageFragmentToCodeQuestionPageFragment,
                  )
              )
            }
          }
        }
      }
    }
  }
}
