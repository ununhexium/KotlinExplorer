package net.lab0.kotlinexplorer.framework.presentation.composable.frame

import android.app.Activity
import android.widget.Toast
import androidx.activity.compose.registerForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.hilt.navigation.compose.hiltNavGraphViewModel
import androidx.navigation.NavHostController
import com.firebase.ui.auth.AuthUI
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import net.lab0.kotlinexplorer.R
import net.lab0.kotlinexplorer.framework.presentation.activity.profile.UserProfileViewModel
import net.lab0.kotlinexplorer.framework.presentation.composable.UserProfile
import net.lab0.kotlinexplorer.framework.presentation.intent.Auth


@Composable
fun UserProfileUi(
  topLevelNavController: NavHostController,
) {

  TopLevelScaffold(
    navController = topLevelNavController,
    title = "Profile",
    scaffoldState = rememberScaffoldState(),
    quickScreens = listOf(TopLevelScreen.Profile, TopLevelScreen.Chapters, TopLevelScreen.Tools)
  ) {
    val userProfileViewModel: UserProfileViewModel = hiltNavGraphViewModel()
    userProfileViewModel.refreshUserData()

    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()

    val loginActivityLauncher = registerForActivityResult(
      ActivityResultContracts.StartActivityForResult(),
    ) { result ->
      if (result.resultCode == Activity.RESULT_OK) {
        // logged in
        Toast.makeText(context, "Signed in", Toast.LENGTH_SHORT).show()
        userProfileViewModel.refreshUserData()
      } else {
        Toast.makeText(context, "Sign in failed", Toast.LENGTH_LONG).show()
      }
    }

    val state = userProfileViewModel.uiDataState.collectAsState().value
    val placeholder = painterResource(R.drawable.ic_kotlin_logo)

    UserProfile(
      email = state.user?.email,
      profilePicturePlaceholder = placeholder,
      logIn = {
        loginActivityLauncher.launch(
          AuthUI.getInstance()
            .createSignInIntentBuilder()
            .setAvailableProviders(
              listOf(
                AuthUI.IdpConfig.EmailBuilder().build(),
              )
            )
            .build()
        )
      },
      logOut = {
        val task = Auth.logOut(context)
        task.addOnSuccessListener {
          userProfileViewModel.refreshUserData()
          Toast.makeText(context, "Logged out", Toast.LENGTH_SHORT).show()
        }
        task.addOnFailureListener {
          Toast.makeText(context, "Failed to log out", Toast.LENGTH_LONG).show()
        }
        coroutineScope.launch {
          task.await()
        }
      },
      uid = state.user?.uid ?: "None"
    )
  }
}
