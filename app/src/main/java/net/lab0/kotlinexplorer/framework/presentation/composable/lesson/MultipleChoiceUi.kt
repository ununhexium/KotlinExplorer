package net.lab0.kotlinexplorer.framework.presentation.composable.lesson

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import net.lab0.kotlinexplorer.framework.presentation.activity.lesson.multiplechoice.MultipleChoiceViewModel
import net.lab0.kotlinexplorer.framework.presentation.activity.lesson.multiplechoice.mvi.MultipleChoiceUiState

@Composable
fun MultipleChoiceUi(
  onNextPage: () -> Unit,
) {
  val model: MultipleChoiceViewModel = viewModel()
  val collected = model.uiDataState.collectAsState()
  val state: MultipleChoiceUiState = collected.value

  MultipleChoicePage(
    state = state,
    toggleAnswer = model::toggle,
    onValidate = model::validate,
    onNextPage = onNextPage
  )
}

