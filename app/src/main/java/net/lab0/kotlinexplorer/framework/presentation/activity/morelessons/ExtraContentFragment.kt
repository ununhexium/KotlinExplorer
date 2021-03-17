package net.lab0.kotlinexplorer.framework.presentation.activity.morelessons

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.compose.material.rememberScaffoldState
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import net.lab0.kotlinexplorer.business.interactor.abstraction.RequestExtraLessons
import net.lab0.kotlinexplorer.framework.presentation.composable.frame.TopLevelScaffold
import net.lab0.kotlinexplorer.framework.presentation.composable.morelessons.MoreLessonsUi
import net.lab0.kotlinexplorer.framework.ui.theme.KotlinExplorerTheme

@ExperimentalCoroutinesApi
class ExtraContentFragment(
  val requestExtraLessons: RequestExtraLessons,
) : Fragment() {

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
    return ComposeView(requireContext()).also {
      it.setContent {
        val scaffoldState = rememberScaffoldState()

//        KotlinExplorerTheme {
//          TopLevelScaffold(
//            title = "More Lessons",
//            scaffoldState = scaffoldState,
//          ) {
//            MoreLessonsUi(
//              onValidate = { liking, whyMore, comment ->
//                lifecycleScope.launch {
//                  requestExtraLessons.invoke(liking, whyMore, comment).collect()
//                  Toast.makeText(context, "We'll work on that! :)", Toast.LENGTH_LONG).show()
//                  findNavController().popBackStack()
//                }
//              }
//            )
//          }
//        }
      }
    }
  }
}
