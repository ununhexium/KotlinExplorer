package net.lab0.kotlinexplorer.framework.presentation.activity.welcome.login.mvi

import net.lab0.kotlinexplorer.mvi.UiEvent

sealed class LoginUiEvent: UiEvent {
  object Login : LoginUiEvent()

  object CheckCanLogin: LoginUiEvent()

  object Empty : LoginUiEvent()
}
