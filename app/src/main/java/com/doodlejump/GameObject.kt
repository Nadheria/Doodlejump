package com.doodlejump

import android.content.Context
import android.graphics.*

abstract class GameObject(val size: Vector, var pos: Vector, var sprite: Int) {

    // Left, Top, Right, Bottom
    var hitbox = RectF(0F, 0F, 0F, 0F)
    init {
        move(pos)
    }
    abstract fun whenHit(player: Player)
    open fun isHit(box: RectF): Boolean { return box.intersect(hitbox) }

    open fun draw(canvas: Canvas, context: Context) {
        var res = BitmapFactory.decodeResource(context.resources, sprite)
        var bitmap = Bitmap.createScaledBitmap(res, size.x.toInt(), size.y.toInt(), false)
        canvas.drawBitmap(bitmap, pos.x, canvas.height - pos.y - size.y, Paint())
    }

    fun move(newPosition: Vector) {
        this.pos = newPosition
        hitbox.left = newPosition.x
        hitbox.top = newPosition.y - size.y
        hitbox.right = newPosition.x + size.x
        hitbox.bottom = newPosition.y
    }

}