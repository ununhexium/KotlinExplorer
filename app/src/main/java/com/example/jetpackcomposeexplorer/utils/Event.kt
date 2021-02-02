package com.example.jetpackcomposeexplorer.utils

/**
 * Used as a wrapper for data that is exposed via a LiveData that represents an event.
 */
class Event<T>(private val content: T) {

    var hasBeenHandled = false
        private set // Allow external read but not write

    /**
     * Returns the content and prevents its use again.
     */
    fun getContentIfNotHandled(): T? {
        return if (hasBeenHandled) {
            null
        } else {
            hasBeenHandled = true
            content
        }
    }

    /**
     * Processes the content if it's not been processed already
     */
    fun handle(handler: (T) -> Unit) {
        if(!hasBeenHandled) {
            hasBeenHandled = true
            handler(content)
        }
    }

    /**
     * Returns the content, even if it's already been handled.
     */
    fun peekContent(): T = content

    override fun toString(): String {
        return "Event(content=$content,hasBeenHandled=$hasBeenHandled)"
    }

    companion object {

        // we don't want an event if there's no data
        fun <T> new(data: T?): Event<T>? {
            data?.let {
                return Event(it)
            }
            return null
        }
    }
}
