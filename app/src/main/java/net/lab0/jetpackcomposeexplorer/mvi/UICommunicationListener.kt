package net.lab0.jetpackcomposeexplorer.mvi

interface UICommunicationListener {
    fun onResponseReceived(
        message: String,
    )
}
