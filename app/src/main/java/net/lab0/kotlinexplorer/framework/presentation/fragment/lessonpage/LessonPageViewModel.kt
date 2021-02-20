package net.lab0.kotlinexplorer.framework.presentation.fragment.lessonpage

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import net.lab0.kotlinexplorer.framework.presentation.fragment.lessonpage.mvi.LessonPageUiEvent
import net.lab0.kotlinexplorer.framework.presentation.fragment.lessonpage.mvi.LessonPageUiState
import net.lab0.kotlinexplorer.mvi.BaseViewModel
import net.lab0.kotlinexplorer.mvi.Resource
import net.lab0.kotlinexplorer.utils.Do

class LessonPageViewModel(
    val ioDispatcher: CoroutineDispatcher = Dispatchers.IO,
) : BaseViewModel<LessonPageUiEvent, LessonPageUiState>(
    LessonPageUiEvent.Empty,
    LessonPageUiState(0, listOf(), listOf()),
    ioDispatcher
) {
  override suspend fun doJobForEvent(event: LessonPageUiEvent) {
    Do exhaustive when (event) {
      LessonPageUiEvent.Empty -> Unit
      LessonPageUiEvent.Undo -> {
        updateUi {
          it.copy(selectedAnswers = it.selectedAnswers.dropLast(1))
        }
      }
      LessonPageUiEvent.Reset -> TODO()
      LessonPageUiEvent.Validate -> TODO()
      LessonPageUiEvent.Next -> TODO()
      is LessonPageUiEvent.SelectAnswer -> {
        updateUi {
          it.copy(selectedAnswers = it.selectedAnswers + event.answer)
        }
      }
    }
  }

  fun init(page: Int, answers: List<Answer>) {
    updateUi {
      it.copy(page = page, choices = answers)
    }
  }

  fun undo() {
    emitFastEvent(LessonPageUiEvent.Undo)
  }

  fun select(answer: Answer) {
    emitFastEvent(LessonPageUiEvent.SelectAnswer(answer))
  }

  var score = 0

  fun incScore() {
    viewModelScope.launch {
      delay(1000)
      score += 1
      score
    }
  }
}
