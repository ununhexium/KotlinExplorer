package net.lab0.kotlinexplorer.framework.presentation.activity.profile.state

import net.lab0.kotlinexplorer.mvi.UiEvent

sealed class UserProfileEvent : UiEvent {
  object Empty : UserProfileEvent()
  object RefreshUser : UserProfileEvent()
}
