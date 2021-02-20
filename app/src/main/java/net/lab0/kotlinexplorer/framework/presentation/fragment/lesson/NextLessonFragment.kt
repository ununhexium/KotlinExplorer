package net.lab0.kotlinexplorer.framework.presentation.fragment.lesson

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import net.lab0.kotlinexplorer.business.domain.LessonBrowser
import net.lab0.kotlinexplorer.framework.presentation.composable.lesson.NextLessonPage

class NextLessonFragment : Fragment() {
  private val args: NextLessonFragmentArgs by navArgs()

  override fun onCreateView(
      inflater: LayoutInflater,
      container: ViewGroup?,
      savedInstanceState: Bundle?
  ): View? {
    return ComposeView(requireContext()).also {
      it.setContent {
        NextLessonPage(
            lessonId = args.lessonId,
            goToChapters = {
              findNavController().navigate(
                  NextLessonFragmentDirections.actionNextLessonFragmentToChapterListFragment()
              )
            },
            goToNextLesson = {
              // TODO: ugly check here and in the component too. Must be checked only once.
              LessonBrowser.getNextLessonInChapter(args.lessonId)?.let { lesson ->
                findNavController().navigate(
                    NextLessonFragmentDirections.actionNextLessonFragmentToLessonFragment(lesson.id)
                )
              }
            }
        )
      }
    }
  }
}
