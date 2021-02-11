package com.example.jetpackcomposeexplorer.mvi

interface UICommunicationListener {
    fun onResponseReceived(
        message: String,
    )
}
