package com.doodlejump

import android.graphics.*

class Player(pos0: Vector): GameObject(Vector(136F, 136F), pos0, R.drawable.player), IUpdate {

    var acceleration = Vector(0F, GRAVITY)
    var speed = Vector(0F, 0F)
    var alive = true
    var hitable = true
    private var jumpBox = hitbox
    private var speedMultiplier = 1.0F // Speed multiplier that increases with score

    companion object {
        const val JUMP_SPEED = 70F
        const val GRAVITY = -6F
        const val JUMP_HEIGHT = JUMP_SPEED * JUMP_SPEED / (-2 * GRAVITY)
    }

    override fun update(game: GameManager) {
        // Increase speed multiplier based on score
        when {
            game.score >= 800 -> speedMultiplier = 1.4F
            game.score >= 1400 -> speedMultiplier = 1.6F
            game.score >= 2000 -> speedMultiplier = 1.8F
            game.score >= 2500 -> speedMultiplier = 2.0F
            game.score >= 3000 -> speedMultiplier = 2.2F
            game.score >= 3500 -> speedMultiplier = 2.4F
            game.score >= 4000 -> speedMultiplier = 2.6F
            game.score >= 4500 -> speedMultiplier = 2.8F

        }

        speed += acceleration * GameManager.TIME_CONSTANT * speedMultiplier
        move(speed * GameManager.TIME_CONSTANT * speedMultiplier)

        if(pos.y < size.y) if(game.score > 0) die() else rebound()
        if(pos.x < 0 - size.x) pos.x = GameManager.WIDTH
        if(pos.x > GameManager.WIDTH) pos.x = 0F
        if(pos.y > GameManager.HEIGHT / 2) {
            game.moveObjects(pos.y - GameManager.HEIGHT / 2)
            pos.y = GameManager.HEIGHT / 2
        }
    }

    override fun draw(game: GameManager) {
        var wd = game.width / GameManager.WIDTH
        var hd = game.height / GameManager.HEIGHT
        if(ressource == null) ressource = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(game.context.resources, sprite), (size.x * wd).toInt(), (size.y * hd).toInt(), false)
        ressource?.let { game.canvas.drawBitmap(it.flip(if(speed.x >= 0) 1f else -1f, 1f, it.width / 2f, it.height / 2f), pos.x * wd, (GameManager.HEIGHT - pos.y) * hd, Paint()) }
    }

    private fun Bitmap.flip(x: Float, y: Float, cx: Float, cy: Float): Bitmap {
        val matrix = Matrix().apply { postScale(x, y, cx, cy) }
        return Bitmap.createBitmap(this, 0, 0, width, height, matrix, true)
    }

    fun rebound() {
        if(speed.y < 0 && alive) speed.y = JUMP_SPEED * speedMultiplier
    }

    fun die() {
        acceleration.y = 0F
        speed.x = 0F
        alive = false
    }

    fun checkCollisions(objects: ArrayList<GameObject>) {
        if(!hitable) return
        jumpBox = if(speed.x >= 0) RectF(hitbox.left, hitbox.top, hitbox.right - 40f, hitbox.bottom - 115f)
        else RectF(hitbox.left + 40f, hitbox.top, hitbox.right, hitbox.bottom - 115f)

        if(alive) objects.forEach {
            if(it.isHit(if(it is IJumpable) jumpBox else hitbox))
                it.whenHit(this)
        }
    }

    fun changeJetpack(enabled: Boolean, game: GameManager) {
        var wd = game.width / GameManager.WIDTH
        var hd = game.height / GameManager.HEIGHT
        if(enabled) {
            size = Vector(136F, 136F)
            ressource = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(game.context.resources, R.drawable.jetpackplayer), (size.x * wd).toInt(), (size.y * hd).toInt(), false)
            hitable = false
        } else {
            size = Vector(136F, 136F)
            ressource = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(game.context.resources, sprite), (size.x * wd).toInt(), (size.y * hd).toInt(), false)
            hitable = true
        }
    }

    override fun whenHit(player: Player) {
        // Useless function due to inheritance
    }
}
