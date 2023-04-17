package com.doodlejump

import android.content.Context
import android.graphics.*

class Player(pos0: Vector): GameObject(Vector(311F / SCALE, 272F / SCALE), pos0, R.drawable.player), IUpdate {


    var acceleration = Vector(0F, GRAVITY)
    var speed = Vector(0F, 0F)
    var gameStarted = false
    var alive = true
    var ressource: Bitmap? = null

    companion object {
        const val SCALE = 1.5f
        const val JUMP_SPEED = 100F
        const val GRAVITY = -10F
        const val JUMP_HEIGHT = JUMP_SPEED * JUMP_SPEED / (-2 * GRAVITY)
    }
    override fun update(game: GameManager) {
        speed += acceleration * GameManager.TIME_CONSTANT
        move(pos + speed * GameManager.TIME_CONSTANT)
        if(pos.y < 0) rebound()
        if(pos.x < 0 - size.x) pos.x = game.width.toFloat()
        if(pos.x > game.width) pos.x = 0F
        if(pos.y > game.height / 2) {
            game.moveObjects(pos.y - game.height.toFloat() / 2)
            pos.y = game.height.toFloat() / 2
        }
    }

    override fun draw(canvas: Canvas, context: Context) {
        if(ressource == null) ressource = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.resources, sprite), size.x.toInt(), size.y.toInt(), false)
        ressource?.let { canvas.drawBitmap(it.flip(if(speed.x > 0) 1f else -1f, 1f, it.width / 2f, it.height / 2f), pos.x, canvas.height - pos.y - size.y, Paint()) }
    }

    private fun Bitmap.flip(x: Float, y: Float, cx: Float, cy: Float): Bitmap {
        val matrix = Matrix().apply { postScale(x, y, cx, cy) }
        return Bitmap.createBitmap(this, 0, 0, width, height, matrix, true)
    }

    fun rebound() {
        if(speed.y < 0) speed.y = JUMP_SPEED
    }

    fun die() {
        speed.y = -125F
        acceleration.y = 0F
        alive = false
    }

    fun checkCollisions(objects: ArrayList<GameObject>) {
        if(alive) objects.forEach { if(it.isHit(hitbox)) it.whenHit(this) }
    }

    /* Interface pour les objets when hit sachant que le joueur n'en a pas besoin
    car la méthode permet de gérer les collisions avec le joueur (et peut être les monstres)
    On pourrait donc peut être faire une interface living entity qui possède la méthode en question ?
     */
    override fun whenHit(player: Player) {
        // Useless function due to inheritance
    }
}