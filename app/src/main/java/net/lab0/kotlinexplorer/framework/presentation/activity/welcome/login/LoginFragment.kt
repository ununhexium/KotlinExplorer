package net.lab0.kotlinexplorer.framework.presentation.activity.welcome.login

import android.app.Activity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.firebase.ui.auth.AuthUI
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import net.lab0.kotlinexplorer.framework.presentation.activity.welcome.login.mvi.LoginUiEvent
import net.lab0.kotlinexplorer.framework.presentation.activity.welcome.login.mvi.LoginUiState
import net.lab0.kotlinexplorer.framework.presentation.composable.login.LoginUi
import net.lab0.kotlinexplorer.framework.ui.theme.KotlinExplorerTheme
import net.lab0.kotlinexplorer.mvi.BaseFragment

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class LoginFragment(
  private val viewModelFactory: ViewModelProvider.Factory,
) : BaseFragment<LoginUiEvent, LoginUiState>() {

  override val viewModel: LoginViewModel by viewModels { viewModelFactory }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    viewModel.checkCanLogin()
  }

  private val loginActivity = registerForActivityResult(
    ActivityResultContracts.StartActivityForResult()
  ) { result ->
    if (result.resultCode == Activity.RESULT_OK) {
      // logged in
      findNavController().navigate(
        LoginFragmentDirections.actionLoginFragmentToLessonGraph()
      )
    } else {
      Toast.makeText(context, "Login failed", Toast.LENGTH_LONG).show()
    }
  }

  override fun onCreateComposeView(view: ComposeView) {
    view.setContent {
      val collected = viewModel.uiDataState.collectAsState()
      val state = collected.value

      KotlinExplorerTheme {
        LoginUi(
          username = state.username,
          onStartApp = {
            findNavController().navigate(
              LoginFragmentDirections.actionLoginFragmentToLessonGraph()
            )
          },
//          login = {
//            loginActivity.launch(
//              AuthUI.getInstance()
//                .createSignInIntentBuilder()
//                .setAvailableProviders(
//                  listOf(
//                    AuthUI.IdpConfig.EmailBuilder().build(),
//                  )
//                )
//                .build()
//            )
//          },
          loginAnonymously = {
            FirebaseAuth.getInstance().signInAnonymously()
            findNavController().navigate(
              LoginFragmentDirections.actionLoginFragmentToLessonGraph()
            )
          },
        )
      }
    }
  }
}
