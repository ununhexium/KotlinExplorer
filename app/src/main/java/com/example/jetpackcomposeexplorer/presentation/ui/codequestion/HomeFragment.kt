package com.example.jetpackcomposeexplorer.presentation.ui.codequestion

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.jetpackcomposeexplorer.ui.frame.ExploreDrawer

class HomeFragment : Fragment() {
  override fun onCreateView(
      inflater: LayoutInflater,
      container: ViewGroup?,
      savedInstanceState: Bundle?,
  ): View {
    return ComposeView(requireContext()).apply {
      setContent {
        val scaffoldState = rememberScaffoldState()

        Scaffold(
            drawerContent = {
              ExploreDrawer(
                  onSelection = {
                    scaffoldState.drawerState.close()
                  }
              )
            }
        ) {
          Text(text = "Home page")
        }
      }
    }
  }
}
