package net.lab0.kotlinexplorer.mvi

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.flow.collect

abstract class BaseFragment<Event, Data> : Fragment()
    where Data : UiState, Event : UiEvent {

  val TAG = this::class.java.canonicalName

  protected abstract val viewModel: BaseViewModel<Event, Data>

  override fun onAttach(context: Context) {
    super.onAttach(context)

    lifecycleScope.launchWhenStarted {
      viewModel.errors.collect { lookOnce ->
        val listener = context as? UICommunicationListener
        if (listener != null) {
          lookOnce.observe {
            listener.onResponseReceived(it)
          }
        }
      }
    }
  }

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?,
  ): View {
    super.onCreateView(inflater, container, savedInstanceState)

    val view = ComposeView(requireContext())

    onCreateComposeView(view)

    return view
  }

  abstract fun onCreateComposeView(view: ComposeView)
}
