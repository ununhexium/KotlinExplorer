package net.lab0.kotlinexplorer.business.domain.state

interface StateEvent {
  val errorInfo: String
    get() = "Error on event (${this::class.java.simpleName})"

  val eventName: String
    get() = this::class.java.simpleName

  val shouldDisplayProgressBar: Boolean
    get() = false
}
