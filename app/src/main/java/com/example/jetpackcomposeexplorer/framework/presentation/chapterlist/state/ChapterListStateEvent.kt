package com.example.jetpackcomposeexplorer.framework.presentation.chapterlist.state

import com.example.jetpackcomposeexplorer.mvi.UiEvent

sealed class ChapterListStateEvent : UiEvent {
  object Empty: ChapterListStateEvent()
  object LoadLessonsInProgress: ChapterListStateEvent()
  object LoadAllChapters: ChapterListStateEvent()
}
