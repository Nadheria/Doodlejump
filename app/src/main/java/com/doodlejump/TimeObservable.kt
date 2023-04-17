package com.doodlejump

class TimeObservable(var duration: Int, val linkedObject: GameObject) {

    fun update() {
        duration --
    }
}