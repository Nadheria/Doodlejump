package com.doodlejump

import android.content.Context
import android.graphics.*

class Player(pos0: Vector): GameObject(Vector(311F / SCALE, 272F / SCALE), pos0, R.drawable.player), IUpdate {


    var acceleration = Vector(0F,-10F)
    var speed = Vector(0F, 0F)

    companion object {
        const val SCALE = 1.5f
    }
    override fun update(game: GameManager) {
        speed += acceleration * GameManager.TIME_CONSTANT
        move(pos + speed * GameManager.TIME_CONSTANT)
        if(pos.y < 0) rebound()
        if(pos.x < 0 - size.x) pos.x = game.width.toFloat()
        if(pos.x > game.width) pos.x = 0F
    }

    override fun draw(canvas: Canvas, context: Context) {
        var res = BitmapFactory.decodeResource(context.resources, sprite)
        var bitmap = Bitmap.createScaledBitmap(res, size.x.toInt(), size.y.toInt(), false)
        canvas.drawBitmap(bitmap.flip(if(speed.x > 0) 1f else -1f, 1f, bitmap.width / 2f, bitmap.height / 2f), pos.x, canvas.height - pos.y - size.y, Paint())
    }

    private fun Bitmap.flip(x: Float, y: Float, cx: Float, cy: Float): Bitmap {
        val matrix = Matrix().apply { postScale(x, y, cx, cy) }
        return Bitmap.createBitmap(this, 0, 0, width, height, matrix, true)
    }

    fun rebound() {
        if(speed.y < 0) speed.y = 125F
    }

    fun checkCollisions(objects: ArrayList<GameObject>) {
        objects.forEach { if(it !is Player && it.isHit(hitbox)) it.whenHit(this) }
    }

    /* Interface pour les objets when hit sachant que le joueur n'en a pas besoin
    car la méthode permet de gérer les collisions avec le joueur (et peut être les monstres)
    On pourrait donc peut être faire une interface living entity qui possède la méthode en question ?
     */
    override fun whenHit(player: Player) {
        // Useless function due to inheritance
    }
}