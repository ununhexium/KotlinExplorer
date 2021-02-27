package net.lab0.kotlinexplorer.framework.presentation.fragment.chapterlist.state

import android.content.Context
import net.lab0.kotlinexplorer.mvi.UiEvent

sealed class ChapterListStateEvent : UiEvent {
  object Empty: ChapterListStateEvent()
  object LoadLessonsInProgress: ChapterListStateEvent()
  object LoadAllChapters: ChapterListStateEvent()
  class RequestMoreChapters(val context:Context): ChapterListStateEvent()
}
