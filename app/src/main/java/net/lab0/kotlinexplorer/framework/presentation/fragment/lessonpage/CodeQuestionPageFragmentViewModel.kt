package net.lab0.kotlinexplorer.framework.presentation.fragment.lessonpage

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import net.lab0.kotlinexplorer.business.domain.Chapter
import net.lab0.kotlinexplorer.business.domain.LessonPage
import net.lab0.kotlinexplorer.framework.presentation.fragment.lessonpage.mvi.LessonPageUiEvent
import net.lab0.kotlinexplorer.framework.presentation.fragment.lessonpage.mvi.LessonPageUiState
import net.lab0.kotlinexplorer.mvi.BaseViewModel
import net.lab0.kotlinexplorer.utils.Do

class CodeQuestionPageFragmentViewModel(
    ioDispatcher: CoroutineDispatcher = Dispatchers.IO,
) : BaseViewModel<LessonPageUiEvent, LessonPageUiState>(
    LessonPageUiEvent.Empty,
    LessonPageUiState(0, LessonPage.CodeQuestionPage.EMPTY, Chapter.EMPTY, listOf()),
    ioDispatcher
) {
  override suspend fun doJobForEvent(event: LessonPageUiEvent) {
    Do exhaustive when (event) {
      LessonPageUiEvent.Empty -> Unit
      LessonPageUiEvent.Undo -> {
        updateUi {
          it.lockableCopy(selectedAnswers = it.selectedAnswers.dropLast(1))
        }
      }
      LessonPageUiEvent.Reset -> {
        updateUi {
          it.lockableCopy(selectedAnswers = listOf())
        }
      }
      LessonPageUiEvent.Validate -> {
        updateUi {
          it.copy(locked = true)
        }
      }
      is LessonPageUiEvent.SelectAnswer -> {
        if (event.answer in uiDataState.value.possibleChoices.indices) {
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
    emitFastEvent(LessonPageUiEvent.Undo)
  }

  fun reset() {
    emitFastEvent(LessonPageUiEvent.Reset)
  }

  fun select(answer: Int) {
    emitFastEvent(LessonPageUiEvent.SelectAnswer(answer))
  }

  fun validate() {
    emitFastEvent(LessonPageUiEvent.Validate)
  }
}
