package net.lab0.jetpackcomposeexplorer.mvi

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import net.lab0.jetpackcomposeexplorer.utils.Do
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

abstract class BaseViewModel<Event, State>(
    initialUiState: Event,
    initialDataState: State,
) : ViewModel() {
  val TAG: String = this::class.java.canonicalName ?: "NoCanonicalName"

  private val _errors = MutableStateFlow(
      ObserveOnce("", true /* Don't show this initial message */)
  )

  private val _uiState = MutableStateFlow(initialUiState)
  private val _uiDataState: MutableStateFlow<State> = MutableStateFlow(initialDataState)

  private val jobsQueue = mutableMapOf<Event, Job>()

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
        Resource.Empty -> Unit
      }
    }
  }

  fun updateUi(action: (State) -> State) {
    _uiDataState.value = action(_uiDataState.value)
  }

  var count = 0
  fun emitSlowEvent(event: Event) {
    count++

    jobsQueue.computeIfAbsent(event) {
      viewModelScope.launch {
        _loading.value = true
        withContext(Dispatchers.IO) {
          doJobForEvent(event)
          jobsQueue.remove(event)
          _loading.value = jobsQueue.isNotEmpty()
          Log.d(TAG, "emitSlowEvent: ${_loading.value}")
        }
      }
    }
  }

  fun emitFastEvent(event: Event) {
    jobsQueue.computeIfAbsent(event) {
      viewModelScope.launch {
        withContext(Dispatchers.IO) {
          doJobForEvent(event)
          jobsQueue.remove(event)
        }
      }
    }
  }

  abstract suspend fun doJobForEvent(event: Event)
}
