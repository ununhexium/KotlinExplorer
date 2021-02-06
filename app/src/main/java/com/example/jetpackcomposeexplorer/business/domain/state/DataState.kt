package com.example.jetpackcomposeexplorer.business.domain.state


data class DataState<T>(
    var stateMessage: StateMessage? = null,
    var data: T? = null,
    var stateEvent: StateEvent? = null,
) {

  companion object {

    fun <T> error(
        response: Response,
        stateEvent: StateEvent?,
    ): DataState<T> {
      return DataState(
          stateMessage = StateMessage(
              response
          ),
          data = null,
          stateEvent = stateEvent
      )
    }

    fun <T> data(
        response: Response? = null,
        data: T? = null,
        stateEvent: StateEvent? = null,
    ): DataState<T> {
      return DataState(
          stateMessage = response?.let {
            StateMessage(
                it
            )
          },
          data = data,
          stateEvent = stateEvent
      )
    }
  }
}
