package com.doodlejump

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.RectF

class Projectile(var p0: Vector): GameObject(RectF(p0[0], p0[1], p0[0] + 10, p0[1] + 10), p0) {

    private val projPaint = Paint()
    private val radius = 10.0f

    override fun draw(canvas: Canvas) {
        canvas.drawCircle(p0[0], p0[1], radius, projPaint)
    }

    override fun update() {

    }

    override fun whenHit() {

    }
}