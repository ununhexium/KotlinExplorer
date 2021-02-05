package com.example.jetpackcomposeexplorer.framework.presentation.chapterlist.state

import com.example.jetpackcomposeexplorer.business.domain.state.StateEvent

sealed class ChapterListStateEvent : StateEvent {
  class ReviewLesson(
      lessonId: String,
  ) : ChapterListStateEvent() {
    override val errorInfo = "Error reviewing lesson with id $lessonId"
    override val shouldDisplayProgressBar = true
  }

  class SelectChapter

  // TODO: search in chapters and lesson
}
