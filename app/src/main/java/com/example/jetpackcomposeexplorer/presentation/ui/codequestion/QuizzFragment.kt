package com.example.jetpackcomposeexplorer.presentation.ui.codequestion

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.material.Text
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import com.example.jetpackcomposeexplorer.model.KotlinCodeWithBlanks.Companion.placeholder
import com.example.jetpackcomposeexplorer.presentation.components.CodeQuizPage
import com.example.jetpackcomposeexplorer.presentation.components.Quiz
import com.example.jetpackcomposeexplorer.ui.frame.placeholder

class QuizzFragment : Fragment() {
  override fun onCreateView(
      inflater: LayoutInflater,
      container: ViewGroup?,
      savedInstanceState: Bundle?,
  ): View {
    return ComposeView(requireContext()).apply {
      val viewModel = QuizViewModel(
          listOf(
              CodeQuestionViewModel(
                  "What is the first letter of the greek alphabet",
                  """println("${placeholder(0)}")""",
                  "α Alpha, β beta, γ gamma, ...",
                  1,
                  "alpha", "beta", "gamma"
              ) {
                it.first().text == "alpha"
              },
              CodeQuestionViewModel(
                  "Which of these words are latin words?",
                  """
                    |println("${placeholder(0)}")
                    |println("${placeholder(1)}")
                  """.trimMargin(),
                  "Album: white, disco: I teach",
                  2,
                  "album", "tree", "disco", "wheel"
              ) {
                it.map { it.text }.containsAll(listOf("album", "disco"))
              },
          )
      )

      setContent {
        Quiz(progress = viewModel.progress.value) {
          val page = viewModel.page.value
          if (page != null) {
            CodeQuizPage(model = page, nextQuestion = viewModel::goToNextPage)
          } else {
            Text("Finished")
          }
        }
      }
    }
  }
}
