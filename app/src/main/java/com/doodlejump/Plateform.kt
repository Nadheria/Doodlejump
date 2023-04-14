package com.doodlejump

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.RectF

abstract class Plateform(var pos0: Vector, var colour : Int): GameObject(100F, 10F, pos0) {

    private val platPaint = Paint()

    override fun draw(canvas: Canvas, context: Context) {
        platPaint.color = colour
        canvas.drawRect(hitbox, platPaint)
    }

    override fun isHit(box: RectF): Boolean {
        return false
    };
}