package com.doodlejump

class TimeObservable(var duration: Int, var game: GameManager, private val obj: GameObject) {

    private var started = false
    init {
        game.registerTimeObservable(this)
    }
    fun start() {
        started = true
    }

    fun update() {
        duration --
        if(duration == 0) obj.removed = true
    }
}