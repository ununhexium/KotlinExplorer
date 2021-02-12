package net.lab0.jetpackcomposeexplorer.framework.presentation.fragment.chapterlist.state

import net.lab0.jetpackcomposeexplorer.mvi.UiEvent

sealed class ChapterListStateEvent : UiEvent {
  object Empty: ChapterListStateEvent()
  object LoadLessonsInProgress: ChapterListStateEvent()
  object LoadAllChapters: ChapterListStateEvent()
}
