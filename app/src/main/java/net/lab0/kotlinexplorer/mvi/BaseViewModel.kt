package net.lab0.kotlinexplorer.mvi

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import net.lab0.kotlinexplorer.utils.Do

abstract class BaseViewModel<Event, State>(
    initialUiState: Event,
    initialDataState: State,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : ViewModel() {
  val TAG: String = this::class.java.canonicalName ?: "NoCanonicalName"

  private val _errors = MutableStateFlow(
      ObserveOnce("", true /* Don't show this initial message */)
  )

  private val _uiState = MutableStateFlow(initialUiState)
  private val _uiDataState: MutableStateFlow<State> = MutableStateFlow(initialDataState)

  val errors: StateFlow<ObserveOnce<String>> = _errors
  val uiDataState: StateFlow<State> = _uiDataState

  private val _loading = MutableStateFlow(false)
  val loading: StateFlow<Boolean> = _loading

  suspend fun <T> processResource(flow: Flow<Resource<T>>, handleResult: (T) -> Unit) {
    flow.collect { resource ->
      Do exhaustive when (resource) {
        Resource.EmptyLoadedResource -> Unit
        is Resource.LoadedResource -> handleResult(resource.resource)
        is Resource.FailedResource -> {
          _errors.value = ObserveOnce(resource.message)
          Log.d(TAG, "page: ${resource.message}")
        }
        is Resource.EmptyLoadedResourceWithMessage -> {
          _errors.value = ObserveOnce(resource.message)
        }
        is Resource.LoadedResourceWithMessage -> {
          handleResult(resource.resource)
          _errors.value = ObserveOnce(resource.message)
        }
      }
    }
  }

  fun updateUi(action: (State) -> State) {
    _uiDataState.value = action(_uiDataState.value)
  }

  var count = 0
  fun emitSlowEvent(event: Event) {
    count++

    viewModelScope.launch {
      _loading.value = true
      withContext(ioDispatcher) {
        doJobForEvent(event)
        Log.d(TAG, "emitSlowEvent: ${_loading.value}")
      }
    }
  }

  fun emitFastEvent(event: Event) {
    viewModelScope.launch {
      withContext(ioDispatcher) {
        doJobForEvent(event)
      }
    }
  }

  abstract suspend fun doJobForEvent(event: Event)
}
