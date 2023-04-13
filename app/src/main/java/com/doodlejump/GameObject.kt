package com.doodlejump

import android.graphics.Canvas
import android.graphics.RectF

abstract class GameObject(var hitbox: RectF) {

    abstract fun draw(canvas: Canvas)
    abstract fun update()
    abstract fun whenHit()

    fun isHit(box: RectF): Boolean { return box.intersect(hitbox) }

}