package com.example.jetpackcomposeexplorer.framework.presentation.activity.mainmenu.state

sealed class MainMenuStateEvent {

  class SelectNextChapterEvent(
      val currentChapter: String,
  ) : MainMenuStateEvent()

  object None : MainMenuStateEvent()
}
