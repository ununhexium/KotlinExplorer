package com.example.jetpackcomposeexplorer.business.domain.state

interface StateEvent {
  val errorInfo: String
  val eventName: String
    get() = this::class.java.simpleName
  val shouldDisplayProgressBar: Boolean
}
