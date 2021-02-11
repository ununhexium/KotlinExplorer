package com.example.jetpackcomposeexplorer.framework.presentation.fragment.chapterlist

import com.example.jetpackcomposeexplorer.business.course.data.kotlin.KOTLIN
import com.example.jetpackcomposeexplorer.business.interactor.abstraction.GetAllChapters
import com.example.jetpackcomposeexplorer.business.interactor.abstraction.GetLessonsInProgress
import com.example.jetpackcomposeexplorer.framework.presentation.fragment.chapterlist.state.ChapterListStateEvent
import com.example.jetpackcomposeexplorer.framework.presentation.fragment.chapterlist.state.ChapterListStateEvent.LoadAllChapters
import com.example.jetpackcomposeexplorer.framework.presentation.fragment.chapterlist.state.ChapterListStateEvent.LoadLessonsInProgress
import com.example.jetpackcomposeexplorer.framework.presentation.fragment.chapterlist.state.ChapterListViewState
import com.example.jetpackcomposeexplorer.mvi.BaseViewModel
import com.example.jetpackcomposeexplorer.utils.Do
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
