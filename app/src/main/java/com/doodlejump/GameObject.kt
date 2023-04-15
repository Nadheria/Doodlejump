package com.doodlejump

import android.content.Context
import android.graphics.*

abstract class GameObject(val size: Vector, var pos: Vector, var sprite: Int) {

    // Left, Top, Right, Bottom
    var hitbox = RectF(pos[0], pos[1], pos[0] + size[0], pos[1] + size[1])
    abstract fun update()
    abstract fun whenHit()
    open fun isHit(box: RectF): Boolean { return box.intersect(hitbox) }

    open fun draw(canvas: Canvas, context: Context) {
        var res = BitmapFactory.decodeResource(context.resources, sprite)
        var bitmap = Bitmap.createScaledBitmap(res, size.x.toInt(), size.y.toInt(), false)
        canvas.drawBitmap(bitmap, pos.x, canvas.height - pos.y -size.y, Paint())
    }

    fun move(newPosition: Vector) {
        this.pos = newPosition
        var bound = newPosition + size
        hitbox.left = newPosition.x
        hitbox.top = newPosition.y
        hitbox.right = bound.x
        hitbox.bottom = bound.y
    }

}