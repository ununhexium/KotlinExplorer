package net.lab0.kotlinexplorer.framework.presentation.activity.profile

import android.app.Activity
import android.content.Context
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.painterResource
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.firebase.ui.auth.AuthUI
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import net.lab0.kotlinexplorer.R
import net.lab0.kotlinexplorer.framework.presentation.activity.profile.state.UserProfileEvent
import net.lab0.kotlinexplorer.framework.presentation.activity.profile.state.UserProfileViewState
import net.lab0.kotlinexplorer.framework.presentation.composable.UserProfileUi
import net.lab0.kotlinexplorer.framework.presentation.composable.frame.TopLevelScaffold
import net.lab0.kotlinexplorer.framework.presentation.intent.Auth
import net.lab0.kotlinexplorer.framework.ui.theme.KotlinExplorerTheme
import net.lab0.kotlinexplorer.mvi.BaseFragment
import net.lab0.kotlinexplorer.utils.printLogD

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class UserProfileOverviewFragment(
    private val viewModelFactory: ViewModelProvider.Factory,
) : BaseFragment<UserProfileEvent, UserProfileViewState>() {

  override val viewModel: UserProfileViewModel by viewModels { viewModelFactory }

  private val registration = registerForActivityResult(
      ActivityResultContracts.StartActivityForResult()
  ) { result ->
    if (result.resultCode == Activity.RESULT_OK) {
      // logged in
      viewModel.refreshUserData()
      Toast.makeText(context, "Signed in", Toast.LENGTH_SHORT).show()
    } else {
      Toast.makeText(context, "Sign in failed", Toast.LENGTH_LONG).show()
    }
  }

  override fun onAttach(context: Context) {
    // TODO: remove?
    super.onAttach(context)
  }

  override fun onCreateComposeView(view: ComposeView) {
    view.setContent {
      UserProfileComposable(viewModel)
    }
  }

  @Composable
  fun UserProfileComposable(viewModel: UserProfileViewModel) {
    val scaffoldState = rememberScaffoldState()
    val placeholder = painterResource(R.drawable.ic_kotlin_logo)

    KotlinExplorerTheme {
      TopLevelScaffold(
          title = "Profile",
          scaffoldState = scaffoldState,
          onProfileSelected = { /*Stay here*/ },
          onLessonsSelected = { findNavController().popBackStack() },
          onToolsSelected = { findNavController().popBackStack() },
      ) {
        val state by viewModel.uiDataState.collectAsState()

        printLogD(
            UserProfileOverviewFragment::class.java.simpleName,
            "State changed: ${state.user}"
        )

        UserProfileUi(
            email = state.user?.email,
            profilePicturePlaceholder = placeholder,
            profilePicture = null,
            logIn = {
              registration.launch(
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
              val task = Auth.logOut(requireContext())
              task.addOnSuccessListener {
                viewModel.refreshUserData()
                Toast.makeText(context, "Logged out", Toast.LENGTH_SHORT).show()
              }
              task.addOnFailureListener {
                Toast.makeText(context, "Failed to log out", Toast.LENGTH_LONG).show()
              }
            },
            uid = state.user?.uid ?: "Nope"
        )
      }
    }
  }
}
