package com.doodlejump

class TimeObservable(var duration: Int, private val obj: GameObject) {

    private var started = false
    val maxDuration = duration

    fun start() {
        started = true
    }

    fun update() {
        if(started) duration --
        if(duration <= 0) obj.removed = true
    }
}