package com.doodlejump

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.RectF

class Projectile(var p0: Vector): GameObject(Vector(10F, 10F), p0, 0) {

    private val projPaint = Paint()
    private val radius = 10.0f

    override fun draw(canvas: Canvas, context: Context) {
        canvas.drawCircle(p0[0], p0[1], radius, projPaint)
    }

    override fun update() {

    }

    override fun whenHit() {

    }
}