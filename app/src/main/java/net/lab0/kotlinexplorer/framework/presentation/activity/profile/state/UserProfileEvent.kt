package net.lab0.kotlinexplorer.framework.presentation.activity.profile.state

import android.content.Intent
import androidx.activity.result.ActivityResultLauncher
import net.lab0.kotlinexplorer.mvi.UiEvent

sealed class UserProfileEvent : UiEvent {
  object Empty : UserProfileEvent()
  object RefreshUser : UserProfileEvent()
}
