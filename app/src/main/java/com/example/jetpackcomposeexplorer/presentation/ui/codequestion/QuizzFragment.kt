package com.example.jetpackcomposeexplorer.presentation.ui.codequestion

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.material.Text
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import com.example.jetpackcomposeexplorer.presentation.components.CodeAnswerInput
import com.example.jetpackcomposeexplorer.presentation.components.CodeQuestion
import com.example.jetpackcomposeexplorer.presentation.components.QuizPage
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
            QuizPage(
                question = {
                  CodeQuestion(
                      question = page.question,
                      code = page.codeSample,
                  )
                },
                answerInput = {
                  CodeAnswerInput(onReset = page::reset,
                      onUndo = page::undo,
                      onValidate = {
                        if (page.isCorrectAnswer()) {
                          viewModel.goToNextPage()
                        }
                      },
                      onSelect = page::select,
                      canValidate = page.canValidate.value,
                      canUndoOrReset = page.selected.value.isNotEmpty(),
                      answers = page.answers.value
                  )
                }
            )
          } else {
            Text("Finished")
          }
        }
      }
    }
  }
}
