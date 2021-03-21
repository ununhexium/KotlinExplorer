package net.lab0.kotlinexplorer.framework.presentation.activity.morelessons

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.material.rememberScaffoldState
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import kotlinx.coroutines.ExperimentalCoroutinesApi
import net.lab0.kotlinexplorer.business.interactor.abstraction.RequestExtraLessons

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
