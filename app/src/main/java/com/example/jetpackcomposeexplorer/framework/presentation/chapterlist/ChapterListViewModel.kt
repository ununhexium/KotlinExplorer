package com.example.jetpackcomposeexplorer.framework.presentation.chapterlist

import androidx.lifecycle.MutableLiveData
import com.example.jetpackcomposeexplorer.business.course.data.kotlin.KOTLIN
import com.example.jetpackcomposeexplorer.business.domain.Chapter
import com.example.jetpackcomposeexplorer.business.domain.state.DataState
import com.example.jetpackcomposeexplorer.business.interactor.abstraction.GetAllChapters
import com.example.jetpackcomposeexplorer.business.interactor.abstraction.GetLessonsInProgress
import com.example.jetpackcomposeexplorer.framework.presentation.chapterlist.state.ChapterListStateEvent
import com.example.jetpackcomposeexplorer.framework.presentation.chapterlist.state.ChapterListStateEvent.ListAllChapters
import com.example.jetpackcomposeexplorer.framework.presentation.chapterlist.state.ChapterListStateEvent.ListLessonsInProgress
import com.example.jetpackcomposeexplorer.framework.presentation.chapterlist.state.ChapterListViewState
import com.example.jetpackcomposeexplorer.framework.presentation.common.BaseViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@ExperimentalCoroutinesApi
@FlowPreview
@Singleton
class ChapterListViewModel
@Inject
constructor(
    val getLessonsInProgress: GetLessonsInProgress,
    val getAllChapters: GetAllChapters,
) : BaseViewModel<ChapterListViewState, ChapterListStateEvent>() {

  override fun handleNewData(data: ChapterListViewState) {
    data.let { state ->

      state.chapters?.let {
        setChaptersData(it)
      }

    }
  }

  private fun setChaptersData(chapters: List<Chapter>) {
    val update = getCurrentViewStateOrNew()
    update.chapters = chapters
    setViewState(update)
  }

  override fun initNewViewState(): ChapterListViewState {
    return ChapterListViewState(
        chapters = KOTLIN
    )
  }

  override fun setStateEvent(stateEvent: ChapterListStateEvent) {

    val job: Flow<DataState<ChapterListViewState>?> = when (stateEvent) {

      is ListLessonsInProgress -> {
        getLessonsInProgress {
          ChapterListViewState(chaptersInProgress = it)
        }
      }

      is ListAllChapters -> {
        getAllChapters { list ->
          ChapterListViewState(
              chapters = list
          )
        }
      }
    }
    launchJob(stateEvent, job)
  }

  fun loadChapters() {
    setStateEvent(ListAllChapters)
  }
}
