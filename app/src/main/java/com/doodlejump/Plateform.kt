package com.doodlejump

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.RectF

abstract class Plateform(var pos0: Vector, var colour : Int): GameObject(RectF(pos0[0], pos0[1], pos0[0] + SIZE_X, pos0[1] + SIZE_Y), pos0) {

    val platPaint = Paint()

    companion object {
        const val SIZE_X = 100
        const val SIZE_Y = 10
    }

    override fun draw(canvas: Canvas) {
        platPaint.color = colour
        canvas.drawRect(hitbox, platPaint)
    }

    override fun isHit(box: RectF): Boolean {
        return false
    };
}