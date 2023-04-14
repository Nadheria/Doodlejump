package com.doodlejump

import android.content.Context
import android.graphics.Canvas
import android.graphics.Rect
import android.graphics.RectF

abstract class GameObject(val sizeX: Float, val sizeY: Float, var pos: Vector) {

    val hitbox = RectF(pos[0], pos[1], pos[0] + sizeX, pos[0] + sizeY)
    abstract fun draw(canvas: Canvas, context: Context)
    abstract fun update()
    abstract fun whenHit()

    open fun isHit(box: RectF): Boolean { return box.intersect(hitbox) }

}