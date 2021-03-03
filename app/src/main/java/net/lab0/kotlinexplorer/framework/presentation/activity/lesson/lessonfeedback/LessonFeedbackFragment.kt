package net.lab0.kotlinexplorer.framework.presentation.activity.lesson.lessonfeedback

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import net.lab0.kotlinexplorer.business.domain.feedback.DifficultyRating
import net.lab0.kotlinexplorer.business.domain.feedback.DurationRating
import net.lab0.kotlinexplorer.framework.presentation.composable.feedback.EvaluationTopic
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

    viewModel.init(
        args.lessonId,
        EvaluationTopic(
            "Duration",
            DurationRating.values().toList()
        ) { viewModel.selectDuration(it) },
        EvaluationTopic(
            "Difficulty",
            DifficultyRating.values().toList()
        ) { viewModel.selectDifficulty(it) },
    )
  }

  override fun onCreateView(
      inflater: LayoutInflater,
      container: ViewGroup?,
      savedInstanceState: Bundle?
  ): View {
    return ComposeView(requireContext()).also { view ->
      view.setContent {
        KotlinExplorerTheme() {
          LessonFeedbackPage(
              durationTopic = viewModel.uiDataState.value.durationEvaluation.map<String> {
                Do exhaustiveNonNull when (it) {
                  DurationRating.TOO_SHORT -> "Too short"
                  DurationRating.BALANCED -> "Balanced"
                  DurationRating.TOO_LONG -> "Too long"
                  DurationRating.UNSET -> "No answer"
                }
              },
              difficultyTopic = viewModel.uiDataState.value.difficultyEvaluation.map<String> {
                Do exhaustiveNonNull when (it) {
                  DifficultyRating.TOO_EASY -> "Too easy"
                  DifficultyRating.BALANCED -> "Balanced"
                  DifficultyRating.TOO_HARD -> "Too hard"
                  DifficultyRating.UNSET -> "No answer"
                }
              },
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
