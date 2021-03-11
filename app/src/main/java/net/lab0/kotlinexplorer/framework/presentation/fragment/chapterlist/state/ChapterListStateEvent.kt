package net.lab0.kotlinexplorer.framework.presentation.fragment.chapterlist.state

import net.lab0.kotlinexplorer.mvi.UiEvent

sealed class ChapterListStateEvent : UiEvent {
  object Empty : ChapterListStateEvent()
  object LoadLessonsInProgress : ChapterListStateEvent()
  object LoadAllChapters : ChapterListStateEvent()
}
