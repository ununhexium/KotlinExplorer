package net.lab0.kotlinexplorer.framework.presentation.activity.lesson.lessonfeedback

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import net.lab0.kotlinexplorer.business.domain.feedback.DifficultyRating
import net.lab0.kotlinexplorer.business.domain.feedback.DurationRating
import net.lab0.kotlinexplorer.framework.presentation.composable.feedback.LessonFeedbackPage
import net.lab0.kotlinexplorer.framework.ui.theme.KotlinExplorerTheme
import net.lab0.kotlinexplorer.utils.Do

class LessonFeedbackFragment(
  private val viewModelFactory: ViewModelProvider.Factory,
) : Fragment() {
  private val args: LessonFeedbackFragmentArgs by navArgs()
  val viewModel: LessonFeedbackViewModel by viewModels { viewModelFactory }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    viewModel.init(args.lessonId)
  }

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
    return ComposeView(requireContext()).also { view ->
      view.setContent {
        val uiDataState = viewModel.uiDataState.collectAsState()
        val state = uiDataState.value

        KotlinExplorerTheme {
          LessonFeedbackPage(
            duration = state.durationEvaluation,
            difficulty = state.difficultyEvaluation,
            onDurationSelection = viewModel::selectDuration,
            onDifficultySelection = viewModel::selectDifficulty,
            onValidate = {
              viewModel.validate()
              goToNextLessonFragment()
            },
            onSkip = {
              goToNextLessonFragment()
            },
          )
        }
      }
    }
  }

  private fun goToNextLessonFragment() {
    findNavController().navigate(
      LessonFeedbackFragmentDirections.actionLessonFeedbackFragmentToNextLessonFragment(
        args.lessonId
      )
    )
  }
}
