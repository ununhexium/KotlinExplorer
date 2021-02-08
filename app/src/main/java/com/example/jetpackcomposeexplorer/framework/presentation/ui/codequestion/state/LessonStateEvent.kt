package com.example.jetpackcomposeexplorer.framework.presentation.ui.codequestion.state

import com.example.jetpackcomposeexplorer.business.domain.state.StateEvent

sealed class LessonStateEvent: StateEvent {
  object GoToNextPage : LessonStateEvent()
}
