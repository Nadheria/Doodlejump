package com.doodlejump.boosts

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.RectF
import android.util.Log
import com.doodlejump.*

class Jetpack(iPos: Vector): GameObject(size, iPos, R.drawable.jetpack), IUpdate {

    private var obs = TimeObservable(100, this)
    private var playerResource: Bitmap? = null

    companion object {
        val size = Vector(100F, 135F)
    }

    override fun whenHit(player: Player) {
        hitbox = RectF(0F, 0F, 0F, 0F)
        pos = Vector(-1000F, 1000F)
        obs.start()
    }

    override fun update(game: GameManager) {
        obs.update()
        if(obs.started) {
            var pl = game.player
            pos.y = 1000F
            pl.speed.y = 100F
            var wd = game.width / GameManager.WIDTH
            var hd = game.height / GameManager.HEIGHT
            if(playerResource == null) playerResource = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(game.context.resources, R.drawable.jetpackplayer), (pl.size.x * wd).toInt(), (pl.size.y * hd).toInt(), false)
            pl.ressource = playerResource
            if(obs.duration == 0) game.player.ressource = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(game.context.resources, game.player.sprite), (pl.size.x * wd).toInt(), (pl.size.y * hd).toInt(), false)
        }
    }
}