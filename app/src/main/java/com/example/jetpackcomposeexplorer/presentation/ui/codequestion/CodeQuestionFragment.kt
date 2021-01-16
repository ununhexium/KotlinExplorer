package com.example.jetpackcomposeexplorer.presentation.ui.codequestion

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import com.example.jetpackcomposeexplorer.presentation.components.CodeAnswerInput
import com.example.jetpackcomposeexplorer.presentation.components.CodeQuestion
import com.example.jetpackcomposeexplorer.ui.frame.DefaultVerticalSpacer
import com.example.jetpackcomposeexplorer.ui.frame.KotlinCode
import com.example.jetpackcomposeexplorer.ui.frame.placeholder

class CodeQuestionFragment : Fragment() {
  private val viewModel = CodeQuestionViewModel()

  override fun onCreateView(
      inflater: LayoutInflater,
      container: ViewGroup?,
      savedInstanceState: Bundle?,
  ): View {
    return ComposeView(requireContext()).apply {
      viewModel.setChoices(1, "alpha", "beta", "gamma")

      setContent {
        CodeQuestion(
            question = {
              Column(modifier = Modifier.fillMaxWidth()) {
                Text("The first letter of the greek alphabet")
                DefaultVerticalSpacer()
                if (viewModel.selected.value.isNotEmpty()) {
                  KotlinCode(code = """println("${viewModel.selected.value.first().text}")""")
                } else {
                  KotlinCode(code = """println("${placeholder()}")""")
                }
              }
            },
            answerInput = {
              CodeAnswerInput(
                  onReset = viewModel::reset,
                  onUndo = viewModel::undo,
                  onValidate = { /* TODO */ },
                  onSelect = { viewModel.select(it) },
                  canValidate = viewModel.canValidate.value,
                  canUndoOrReset = viewModel.selected.value.isNotEmpty(),
                  answers = viewModel.answers.value
              )
            }
        )
      }
    }
  }
}
