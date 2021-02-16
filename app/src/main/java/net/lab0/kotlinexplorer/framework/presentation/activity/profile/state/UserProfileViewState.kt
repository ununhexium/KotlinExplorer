package net.lab0.kotlinexplorer.framework.presentation.activity.profile.state

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import net.lab0.kotlinexplorer.mvi.UiState

data class UserProfileViewState(
    val user: FirebaseUser? = FirebaseAuth.getInstance().currentUser,
): UiState
