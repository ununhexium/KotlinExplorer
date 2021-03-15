package net.lab0.kotlinexplorer.framework.presentation.activity.tool.float

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.rememberScaffoldState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import net.lab0.kotlinexplorer.framework.presentation.composable.frame.TopLevelScaffold
import net.lab0.kotlinexplorer.framework.presentation.composable.math.FloatingPointVisualizer
import net.lab0.kotlinexplorer.framework.ui.theme.KotlinExplorerTheme

class FloatingPointToolFragment : Fragment() {
  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
    return ComposeView(requireContext()).also {
      it.setContent {
//        KotlinExplorerTheme {
//          val scaffold = rememberScaffoldState()
//
//          TopLevelScaffold(
//            title = "Floating Point",
//            scaffoldState = scaffold,
//            onProfileSelected = { findNavController().popBackStack() },
//            onLessonsSelected = { findNavController().popBackStack() },
//            onToolsSelected = { findNavController().popBackStack() },
//          ) {
//            Column(Modifier.padding(8.dp)) {
//              FloatingPointVisualizer()
//            }
//          }
//        }
      }
    }
  }
}
