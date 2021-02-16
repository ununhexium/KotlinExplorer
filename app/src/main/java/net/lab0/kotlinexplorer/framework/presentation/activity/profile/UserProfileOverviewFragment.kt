package net.lab0.kotlinexplorer.framework.presentation.activity.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.loadVectorResource
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import net.lab0.kotlinexplorer.R
import net.lab0.kotlinexplorer.framework.presentation.activity.profile.state.UserProfileEvent
import net.lab0.kotlinexplorer.framework.presentation.activity.profile.state.UserProfileViewState
import net.lab0.kotlinexplorer.framework.presentation.components.UserProfileUi
import net.lab0.kotlinexplorer.framework.presentation.components.frame.TopLevelScaffold
import net.lab0.kotlinexplorer.mvi.BaseFragment
import net.lab0.kotlinexplorer.utils.printLogD

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class UserProfileOverviewFragment : BaseFragment<UserProfileEvent, UserProfileViewState>() {

  override val viewModel: UserProfileViewModel by viewModels()

  override fun onCreateComposeView(view: ComposeView) {
    view.setContent {
      UserProfileComposable(viewModel, this@UserProfileOverviewFragment)
    }
  }

  @Composable
  fun UserProfileComposable(viewModel: UserProfileViewModel, fragment: Fragment) {
    val scaffoldState = rememberScaffoldState()
    val placeholder = loadVectorResource(id = R.drawable.ic_kotlin_logo)

    TopLevelScaffold(
        title = "Profile",
        scaffoldState = scaffoldState,
        onProfileSelected = { /*Stay here*/ },
        onLessonsSelected = { /*TODO: dont nagivate with drawer?*/ }
    ) {
      val state by viewModel.uiDataState.collectAsState()

      printLogD(
          UserProfileOverviewFragment::class.java.simpleName,
          "State changed: ${state.user}"
      )

      UserProfileUi(
          state.user?.email,
          placeholder,
          null,
          {
            viewModel.logIn(fragment)
            findNavController().popBackStack()
          },
          {
            viewModel.logOut(fragment.requireContext())
            findNavController().popBackStack()
          }
      )
    }
  }
}
