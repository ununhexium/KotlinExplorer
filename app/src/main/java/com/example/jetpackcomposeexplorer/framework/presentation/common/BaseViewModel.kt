package com.example.jetpackcomposeexplorer.framework.presentation.common

import androidx.lifecycle.*
import com.example.jetpackcomposeexplorer.business.data.util.GenericErrors
import com.example.jetpackcomposeexplorer.business.domain.state.DataChannelManager
import com.example.jetpackcomposeexplorer.business.domain.state.DataState
import com.example.jetpackcomposeexplorer.business.domain.state.MessageType
import com.example.jetpackcomposeexplorer.business.domain.state.Response
import com.example.jetpackcomposeexplorer.business.domain.state.StateEvent
import com.example.jetpackcomposeexplorer.business.domain.state.StateMessage
import com.example.jetpackcomposeexplorer.business.domain.state.UIComponentType
import com.example.jetpackcomposeexplorer.utils.printLogD
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow


@FlowPreview
@ExperimentalCoroutinesApi
abstract class BaseViewModel<ViewState, StateEventImpl> : ViewModel() where StateEventImpl : StateEvent {
  private val _viewState: MutableLiveData<ViewState> = MutableLiveData()

  private val dataChannelManager: DataChannelManager<ViewState> = object : DataChannelManager<ViewState>() {

    override fun handleNewData(data: ViewState) {
      this@BaseViewModel.handleNewData(data)
    }
  }

  val viewState: LiveData<ViewState>
    get() = _viewState

  val shouldDisplayProgressBar: LiveData<Boolean> = dataChannelManager.shouldDisplayProgressBar

  val stateMessage: LiveData<StateMessage?>
    get() = dataChannelManager.messageStack.stateMessage

  // FOR DEBUGGING
  fun getMessageStackSize(): Int {
    return dataChannelManager.messageStack.size
  }

  fun setupChannel() = dataChannelManager.setupChannel()

  abstract fun handleNewData(data: ViewState)

  abstract fun setStateEvent(stateEvent: StateEventImpl)

  fun emitStateMessageEvent(
      stateMessage: StateMessage,
      stateEvent: StateEventImpl,
  ) = flow {
    emit(
        DataState.error<ViewState>(
            response = stateMessage.response,
            stateEvent = stateEvent
        )
    )
  }

  fun emitInvalidStateEvent(stateEvent: StateEventImpl) = flow {
    emit(
        DataState.error<ViewState>(
            response = Response(
                message = GenericErrors.INVALID_STATE_EVENT,
                uiComponentType = UIComponentType.None,
                messageType = MessageType.Error
            ),
            stateEvent = stateEvent
        )
    )
  }

  fun launchJob(
      stateEvent: StateEventImpl,
      jobFunction: Flow<DataState<ViewState>?>,
  ) = dataChannelManager.launchJob(stateEvent, jobFunction)

  fun getCurrentViewStateOrNew(): ViewState {
    return viewState.value ?: initNewViewState()
  }

  fun setViewState(viewState: ViewState) {
    _viewState.value = viewState
  }

  fun clearStateMessage(index: Int = 0) {
    printLogD("BaseViewModel", "clearStateMessage")
    dataChannelManager.clearStateMessage(index)
  }

  fun clearActiveStateEvents() = dataChannelManager.clearActiveStateEventCounter()

  fun clearAllStateMessages() = dataChannelManager.clearAllStateMessages()

  fun printStateMessages() = dataChannelManager.printStateMessages()

  fun cancelActiveJobs() = dataChannelManager.cancelJobs()

  abstract fun initNewViewState(): ViewState

}








