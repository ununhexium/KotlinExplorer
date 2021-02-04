package com.example.jetpackcomposeexplorer.business.data.course

import com.example.jetpackcomposeexplorer.business.domain.state.DataState
import com.example.jetpackcomposeexplorer.business.domain.state.MessageType
import com.example.jetpackcomposeexplorer.business.domain.state.Response
import com.example.jetpackcomposeexplorer.business.domain.state.StateEvent
import com.example.jetpackcomposeexplorer.business.domain.state.UIComponentType

abstract class CourseResponseHandler<ViewState, Data>(
    private val response: CourseResult<Data?>,
    private val stateEvent: StateEvent?,
) {
  suspend fun getResult(): DataState<ViewState>? {

    return when (response) {

      is CourseResult.GenericError -> {
        DataState.error(
            response = Response(
                message = "${stateEvent?.errorInfo()}\n\nReason: ${response.errorMessage.toString()}",
                uiComponentType = UIComponentType.Dialog(),
                messageType = MessageType.Error()
            ),
            stateEvent = stateEvent
        )
      }

      is CourseResult.Success -> {
        handleSuccess(resultObj = response.value)
      }

    }
  }

  abstract suspend fun handleSuccess(resultObj: Data): DataState<ViewState>?

}