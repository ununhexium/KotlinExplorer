package net.lab0.kotlinexplorer.framework.presentation.activity.lesson.codequestion

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import net.lab0.kotlinexplorer.business.domain.Chapter
import net.lab0.kotlinexplorer.business.domain.LessonPage
import net.lab0.kotlinexplorer.framework.presentation.activity.lesson.codequestion.mvi.CodeQuestionUiEvent
import net.lab0.kotlinexplorer.framework.presentation.activity.lesson.codequestion.mvi.CodeQuestionUiState
import net.lab0.kotlinexplorer.mvi.BaseViewModel
import net.lab0.kotlinexplorer.utils.Do

class CodeQuestionViewModel(
    ioDispatcher: CoroutineDispatcher = Dispatchers.IO,
) : BaseViewModel<CodeQuestionUiEvent, CodeQuestionUiState>(
    CodeQuestionUiEvent.Empty,
    CodeQuestionUiState(0, LessonPage.CodeQuestionPage.EMPTY, Chapter.EMPTY, listOf()),
    ioDispatcher
) {

  override suspend fun doJobForEvent(event: CodeQuestionUiEvent) {
    Do exhaustive when (event) {
      CodeQuestionUiEvent.Empty -> Unit
      CodeQuestionUiEvent.Undo -> {
        updateUi {
          it.lockableCopy(selectedAnswers = it.selectedAnswers.dropLast(1))
        }
      }
      CodeQuestionUiEvent.Reset -> {
        updateUi {
          it.lockableCopy(selectedAnswers = listOf())
        }
      }
      CodeQuestionUiEvent.Validate -> {
        updateUi {
          it.copy(locked = true)
        }
      }
      is CodeQuestionUiEvent.SelectAnswer -> {
        if (event.answer in uiDataState.value.choices.indices) {
          updateUi {
            it.lockableCopy(selectedAnswers = (it.selectedAnswers + event.answer))
          }
        } else null
      }
    }
  }

  fun init(pageIndex:Int, lessonPage: LessonPage.CodeQuestionPage, chapter: Chapter) {
    updateUi {
      it.copy(pageIndex = pageIndex, lessonPage = lessonPage, chapter = chapter)
    }
  }

  fun undo() {
    emitFastEvent(CodeQuestionUiEvent.Undo)
  }

  fun reset() {
    emitFastEvent(CodeQuestionUiEvent.Reset)
  }

  fun select(answer: Int) {
    emitFastEvent(CodeQuestionUiEvent.SelectAnswer(answer))
  }

  fun validate() {
    emitFastEvent(CodeQuestionUiEvent.Validate)
  }
}
