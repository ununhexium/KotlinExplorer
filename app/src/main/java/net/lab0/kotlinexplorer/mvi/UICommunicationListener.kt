package net.lab0.kotlinexplorer.mvi

interface UICommunicationListener {
  fun onResponseReceived(
    message: String,
  )
}
