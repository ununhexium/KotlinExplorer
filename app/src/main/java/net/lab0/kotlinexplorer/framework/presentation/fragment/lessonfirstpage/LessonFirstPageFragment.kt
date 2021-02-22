package net.lab0.kotlinexplorer.framework.presentation.fragment.lessonfirstpage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import net.lab0.kotlinexplorer.business.domain.LessonBrowser
import net.lab0.kotlinexplorer.business.domain.LessonPage
import net.lab0.kotlinexplorer.utils.Do

class LessonFirstPageFragment : Fragment() {
  private val args: LessonFirstPageFragmentArgs by navArgs()

  override fun onCreateView(
      inflater: LayoutInflater,
      container: ViewGroup?,
      savedInstanceState: Bundle?
  ): View {
    return ComposeView(requireContext()).also {
      it.setContent {
        val lesson = LessonBrowser.getLessonById(args.lessonId)
        Column(
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
          Text(
              modifier = Modifier.align(Alignment.CenterHorizontally),
              text = lesson.title,
              style = MaterialTheme.typography.h3
          )
          Button(
              modifier = Modifier.align(Alignment.CenterHorizontally),
              onClick = {
                val firstPage = lesson.pages.first()
                Do exhaustive when(firstPage) {
                  is LessonPage.InfoPage ->
                    findNavController().navigate(
                        LessonFirstPageFragmentDirections
                            .actionLessonFirstPageToLessonInfoPageFragment(args.lessonId, 0)
                    )

                  is LessonPage.CodeQuestionPage ->
                    findNavController().navigate(
                        LessonFirstPageFragmentDirections
                            .actionLessonFirstPageToLessonPageFragment(args.lessonId, 0)
                    )

                  is LessonPage.MultipleChoice -> TODO("Multi choice page")
                }
              }
          ) {
            Icon(imageVector = Icons.Default.PlayArrow)
            Text("Start!")
          }
        }
      }
    }
  }
}
