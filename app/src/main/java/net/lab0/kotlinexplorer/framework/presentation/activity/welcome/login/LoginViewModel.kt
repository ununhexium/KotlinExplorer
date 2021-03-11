package net.lab0.kotlinexplorer.framework.presentation.activity.welcome.login

import com.google.firebase.auth.FirebaseAuth
import net.lab0.kotlinexplorer.framework.presentation.activity.welcome.login.mvi.LoginUiEvent
import net.lab0.kotlinexplorer.framework.presentation.activity.welcome.login.mvi.LoginUiState
import net.lab0.kotlinexplorer.mvi.BaseViewModel
import net.lab0.kotlinexplorer.utils.Do

class LoginViewModel(
  private val auth: FirebaseAuth
) : BaseViewModel<LoginUiEvent, LoginUiState>(
  LoginUiEvent.Empty,
  LoginUiState(null),
) {
  override suspend fun doJobForEvent(event: LoginUiEvent) {
    Do exhaustive when (event) {
      LoginUiEvent.CheckCanLogin -> {
        val currentUser = auth.currentUser
        val username = if (currentUser?.isAnonymous == true) {
          null
        } else {
          currentUser?.email ?: currentUser?.uid
        }
        updateUi {
          it.copy(username = username)
        }
      }
      LoginUiEvent.Empty -> Unit
    }
  }

  fun checkCanLogin() {
    emitFastEvent(LoginUiEvent.CheckCanLogin)
  }
}
