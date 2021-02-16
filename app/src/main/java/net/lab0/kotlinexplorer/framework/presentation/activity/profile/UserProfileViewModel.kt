package net.lab0.kotlinexplorer.framework.presentation.activity.profile

import android.content.Context
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import net.lab0.kotlinexplorer.framework.presentation.activity.profile.state.UserProfileEvent
import net.lab0.kotlinexplorer.framework.presentation.activity.profile.state.UserProfileEvent.LogIn
import net.lab0.kotlinexplorer.framework.presentation.activity.profile.state.UserProfileEvent.LogOut
import net.lab0.kotlinexplorer.framework.presentation.activity.profile.state.UserProfileViewState
import net.lab0.kotlinexplorer.framework.presentation.intent.Auth
import net.lab0.kotlinexplorer.mvi.BaseViewModel
import net.lab0.kotlinexplorer.mvi.Resource
import net.lab0.kotlinexplorer.utils.Do
import net.lab0.kotlinexplorer.utils.printLogD

class UserProfileViewModel : BaseViewModel<UserProfileEvent, UserProfileViewState>(
    UserProfileEvent.Empty,
    UserProfileViewState(),
) {
  override suspend fun doJobForEvent(event: UserProfileEvent) {
    Do exhaustive when (event) {
      UserProfileEvent.Empty -> Unit
      is LogIn -> {
        processResource<Unit>(
            flow {
              withContext(Dispatchers.Main) {
                Auth.requestSignIn(event.fragment)
              }
              emit(Resource.EmptyLoadedResourceWithMessage("Logged in"))
            }
        ) {
          updateUi { it.copy(user = FirebaseAuth.getInstance().currentUser) }
          printLogD(this::class.java.simpleName, "userLogIn " + uiDataState.value)
        }
      }
      is LogOut -> {
        processResource<Unit>(
            flow {
              withContext(Dispatchers.Main) {
                Auth.logOut(event.context)
              }
              emit(Resource.EmptyLoadedResourceWithMessage("Logged out"))
            }
        ) {
          updateUi { it.copy(user = FirebaseAuth.getInstance().currentUser) }
          printLogD(this::class.java.simpleName, "userLogOut " + uiDataState.value)
        }
      }
    }
  }

  fun logIn(fragment: Fragment) {
    emitFastEvent(LogIn(fragment))
  }

  fun logOut(context: Context) {
    emitFastEvent(LogOut(context))
  }
}
