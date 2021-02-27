package net.lab0.kotlinexplorer.framework.presentation.activity.welcome.login.mvi

import net.lab0.kotlinexplorer.mvi.UiState

data class LoginUiState(
    val username: String? = null,
) : UiState
