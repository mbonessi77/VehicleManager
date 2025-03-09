package com.example.vehiclemanagement.helpers

class Event<T>(content: T) {
    private val mContent: T? = content

    private var hasBeenHandled = false

    fun contentIfNotHandled(): T? {
        return if (hasBeenHandled) {
            return null
        } else {
            hasBeenHandled = true
            return mContent
        }
    }

}