package com.example.jetpackcomposeexplorer.framework.presentation.chapterlist.state

import com.example.jetpackcomposeexplorer.business.domain.state.StateEvent

sealed class ChapterListStateEvent : StateEvent {
  object ListLessonsInProgress: ChapterListStateEvent() {
    override val errorInfo = "Can't list lessons in progress"
    override val shouldDisplayProgressBar = true
  }

  // TODO: search in chapters and lesson
}
