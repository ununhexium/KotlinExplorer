package net.lab0.kotlinexplorer.framework.presentation.activity.lesson.multiplechoice

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import net.lab0.kotlinexplorer.business.domain.Chapter
import net.lab0.kotlinexplorer.business.domain.LessonPage
import net.lab0.kotlinexplorer.framework.presentation.activity.lesson.multiplechoice.mvi.MultipleChoiceUiEvent
import net.lab0.kotlinexplorer.framework.presentation.activity.lesson.multiplechoice.mvi.MultipleChoiceUiState
import net.lab0.kotlinexplorer.mvi.BaseViewModel
import net.lab0.kotlinexplorer.utils.Do

class MultipleChoiceViewModel(
    ioDispatcher: CoroutineDispatcher = Dispatchers.IO,
) : BaseViewModel<MultipleChoiceUiEvent, MultipleChoiceUiState>(
    MultipleChoiceUiEvent.Empty,
    MultipleChoiceUiState(0, LessonPage.MultipleChoice.EMPTY, Chapter.EMPTY, listOf()),
    ioDispatcher
) {

  override suspend fun doJobForEvent(event: MultipleChoiceUiEvent) {
    Do exhaustive when (event) {

      MultipleChoiceUiEvent.Empty -> Unit

      MultipleChoiceUiEvent.Validate -> {
        updateUi {
          it.copy(locked = true)
        }
      }

      is MultipleChoiceUiEvent.ToggleAnswer -> {
          updateUi {
            if (event.answer in it.selectedAnswers) {
              it.lockableCopy(
                  selectedAnswers = it.selectedAnswers.filterNot { answer ->
                    answer == event.answer
                  }
              )
            } else {
              it.lockableCopy(
                  selectedAnswers = (it.selectedAnswers + event.answer)
              )
            }
          }
      }
    }
  }

  fun init(pageIndex: Int, lessonPage: LessonPage.MultipleChoice, chapter: Chapter) {
    updateUi {
      it.copy(pageIndex = pageIndex, lessonPage = lessonPage, chapter = chapter)
    }
  }

  fun toggle(answer: Int) {
    emitFastEvent(MultipleChoiceUiEvent.ToggleAnswer(answer))
  }

  fun validate() {
    emitFastEvent(MultipleChoiceUiEvent.Validate)
  }
}
