package com.example.submission1

open class Event<out T>(private val content : T) {
    @Suppress("MemberVisibility")
    var hasBeenHandled = false
    private set

    fun getContentIfNotHandled(): T? {
        return if(hasBeenHandled){
            null
        }else{
            hasBeenHandled = true
            content
        }
    }
    fun peekContent(): T = content
}