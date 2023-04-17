package com.doodlejump

import android.content.Context
import android.graphics.*

abstract class GameObject(val size: Vector, var pos: Vector, var sprite: Int) {

    private var ressource: Bitmap? = null
    var hitbox = RectF(0F, 0F, 0F, 0F)

    init {
        move(pos)
    }
    abstract fun whenHit(player: Player)
    open fun isHit(box: RectF): Boolean { return box.intersect(hitbox) }

    open fun draw(canvas: Canvas, context: Context) {
        if(ressource == null) ressource = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.resources, sprite), size.x.toInt(), size.y.toInt(), false)
        ressource?.let { canvas.drawBitmap(it, pos.x, canvas.height - pos.y - size.y, Paint()) }
    }

    fun move(newPosition: Vector) {
        this.pos = newPosition
        hitbox.left = newPosition.x
        hitbox.top = newPosition.y - size.y
        hitbox.right = newPosition.x + size.x
        hitbox.bottom = newPosition.y
    }

}