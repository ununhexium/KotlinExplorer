package net.lab0.kotlinexplorer.framework.presentation.activity.profile

import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.flow
import net.lab0.kotlinexplorer.framework.presentation.activity.profile.state.UserProfileEvent
import net.lab0.kotlinexplorer.framework.presentation.activity.profile.state.UserProfileEvent.RefreshUser
import net.lab0.kotlinexplorer.framework.presentation.activity.profile.state.UserProfileViewState
import net.lab0.kotlinexplorer.mvi.BaseViewModel
import net.lab0.kotlinexplorer.mvi.Resource
import net.lab0.kotlinexplorer.utils.Do
import net.lab0.kotlinexplorer.utils.printLogD

class UserProfileViewModel(
  val auth: FirebaseAuth
) : BaseViewModel<UserProfileEvent, UserProfileViewState>(
  UserProfileEvent.Empty,
  UserProfileViewState(),
) {
  override suspend fun doJobForEvent(event: UserProfileEvent) {
    Do exhaustive when (event) {
      UserProfileEvent.Empty -> Unit
      is RefreshUser -> {
        processResource(
          flow {
            emit(
              Resource.LoadedResource(
                FirebaseAuth.getInstance().currentUser
              )
            )
          }
        ) { user ->
          updateUi { it.copy(user = user) }
          printLogD(this::class.java.simpleName, "userLogIn " + uiDataState.value)
        }
      }
    }
  }

  fun refreshUserData() {
    emitFastEvent(RefreshUser)
  }
}
