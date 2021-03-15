package net.lab0.kotlinexplorer.framework.presentation.activity.lesson

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs

class LessonFirstPageFragment(
  private val viewModelFactory: ViewModelProvider.Factory,
) : Fragment(), NextPageSelectorMixin {
  private val args: LessonFirstPageFragmentArgs by navArgs()
//  private val activityViewModel: LessonViewModel by activityViewModels { viewModelFactory }

//  override fun onCreate(savedInstanceState: Bundle?) {
//    super.onCreate(savedInstanceState)
//    val lesson = LessonBrowser.getLessonById(args.lessonId)
//    activityViewModel.init(lesson)
//  }

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
    return ComposeView(requireContext()).also {
      it.setContent {
//        KotlinExplorerTheme {
//          val lesson = LessonBrowser.getLessonById(args.lessonId)
//          Column(
//            verticalArrangement = Arrangement.SpaceEvenly
//          ) {
//            Text(
//              modifier = Modifier
//                .fillMaxWidth()
//                .align(Alignment.CenterHorizontally),
//              text = lesson.title,
//              textAlign = TextAlign.Center,
//              style = MaterialTheme.typography.h3,
//              color = MaterialTheme.colors.onBackground,
//            )
//            Button(
//              modifier = Modifier.align(Alignment.CenterHorizontally),
//              onClick = nextPage(
//                activityViewModel = activityViewModel,
//                correctness = AnswerCorrectness.NEUTRAL,
//                page = -1,
//                nextPage = 0,
//                lessonId = args.lessonId,
//                navController = findNavController(),
//                navigationToCodeQuestion = LessonFirstPageFragmentDirections::actionLessonFirstPageToCodeQuestionPageFragment,
//                navigationToMultipleChoice = LessonFirstPageFragmentDirections::actionLessonFirstPageToMultipleChoicePageFragment,
//                navigationToInfo = LessonFirstPageFragmentDirections::actionLessonFirstPageToInfoPageFragment,
//                navigationToFeedback = { NoNavigation },
//                navigationToNextChapter = { NoNavigation },
//              )
//            ) {
//              Icon(imageVector = Icons.Default.PlayArrow, "Play arrow")
//              Text("Start!")
//            }
//          }
//        }
      }
    }
  }
}
