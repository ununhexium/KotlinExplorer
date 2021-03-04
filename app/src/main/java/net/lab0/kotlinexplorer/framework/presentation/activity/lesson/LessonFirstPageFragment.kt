package net.lab0.kotlinexplorer.framework.presentation.activity.lesson

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.text.style.TextAlign
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import net.lab0.kotlinexplorer.business.domain.LessonBrowser
import net.lab0.kotlinexplorer.business.domain.LessonPage
import net.lab0.kotlinexplorer.framework.presentation.NoNavigation
import net.lab0.kotlinexplorer.framework.presentation.activity.lesson.mvi.LessonViewModel
import net.lab0.kotlinexplorer.framework.ui.theme.KotlinExplorerTheme
import net.lab0.kotlinexplorer.utils.Do

class LessonFirstPageFragment(
  private val viewModelFactory: ViewModelProvider.Factory,
) : Fragment(), NextPageSelectorMixin {
  private val args: LessonFirstPageFragmentArgs by navArgs()
  private val activityViewModel: LessonViewModel by activityViewModels { viewModelFactory }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    val lesson = LessonBrowser.getLessonById(args.lessonId)
    activityViewModel.init(lesson)
  }

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
    return ComposeView(requireContext()).also {
      it.setContent {
        KotlinExplorerTheme {
          val lesson = LessonBrowser.getLessonById(args.lessonId)
          Column(
            verticalArrangement = Arrangement.SpaceEvenly
          ) {
            Text(
              modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.CenterHorizontally),
              text = lesson.title,
              textAlign = TextAlign.Center,
              style = MaterialTheme.typography.h3,
              color = MaterialTheme.colors.onBackground,
            )
            Button(
              modifier = Modifier.align(Alignment.CenterHorizontally),
              onClick = nextPage(
                  activityViewModel,
                  AnswerCorrectness.NEUTRAL,
                  -1,
                  args.lessonId,
                  findNavController(),
                  navigationToCodeQuestion = LessonFirstPageFragmentDirections::actionLessonFirstPageToCodeQuestionPageFragment,
                  navigationToMultipleChoice = LessonFirstPageFragmentDirections::actionLessonFirstPageToMultipleChoicePageFragment,
                  navigationToInfo = LessonFirstPageFragmentDirections::actionLessonFirstPageToInfoPageFragment,
                  navigationToFeedback = { NoNavigation },
                  navigationToNextChapter = { NoNavigation },
                )
            ) {
              Icon(imageVector = Icons.Default.PlayArrow, "Play arrow")
              Text("Start!")
            }
          }
        }
      }
    }
  }
}
