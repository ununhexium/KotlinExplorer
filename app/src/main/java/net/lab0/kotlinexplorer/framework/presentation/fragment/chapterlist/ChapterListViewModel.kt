package net.lab0.kotlinexplorer.framework.presentation.fragment.chapterlist

import net.lab0.kotlinexplorer.business.course.data.kotlin.KOTLIN
import net.lab0.kotlinexplorer.business.interactor.abstraction.GetAllChapters
import net.lab0.kotlinexplorer.business.interactor.abstraction.GetLessonsInProgress
import net.lab0.kotlinexplorer.framework.presentation.fragment.chapterlist.state.ChapterListStateEvent
import net.lab0.kotlinexplorer.framework.presentation.fragment.chapterlist.state.ChapterListStateEvent.LoadAllChapters
import net.lab0.kotlinexplorer.framework.presentation.fragment.chapterlist.state.ChapterListStateEvent.LoadLessonsInProgress
import net.lab0.kotlinexplorer.framework.presentation.fragment.chapterlist.state.ChapterListViewState
import net.lab0.kotlinexplorer.mvi.BaseViewModel
import net.lab0.kotlinexplorer.utils.Do
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import javax.inject.Inject
import javax.inject.Singleton

@ExperimentalCoroutinesApi
@FlowPreview
@Singleton
class ChapterListViewModel
@Inject
constructor(
    val getLessonsInProgressCount: GetLessonsInProgress,
    val getAllChapters: GetAllChapters,
) : BaseViewModel<ChapterListStateEvent, ChapterListViewState>(
    ChapterListStateEvent.Empty,
    ChapterListViewState(KOTLIN, listOf())
) {

  override suspend fun doJobForEvent(event: ChapterListStateEvent) {
    Do exhaustive when (event) {
      LoadLessonsInProgress -> {
        processResource(
            getLessonsInProgressCount()
        ) { chapters ->
          updateUi {
            it.copy(lessonsInProgress = chapters)
          }
        }
      }

      LoadAllChapters -> {
        processResource(
            getAllChapters()
        ) { chapters ->
          updateUi {
            it.copy(chapters = chapters)
          }
        }
      }

      ChapterListStateEvent.Empty -> Unit
    }
  }

  fun loadChapters() = emitFastEvent(LoadAllChapters)

  fun loadLessonsInProgress() = emitFastEvent(LoadLessonsInProgress)
}
