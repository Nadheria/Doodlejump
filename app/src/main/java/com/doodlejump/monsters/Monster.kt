package com.doodlejump.monsters

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Paint
import com.doodlejump.*
import com.doodlejump.Player

class Monster(pos0: Vector): GameObject(Vector(311F, 272F), pos0, R.drawable.monster){

    override fun whenHit(player: Player) {
        if(player.speed.y < 0 ) {
            removed = true
            player.rebound()
        }
        if(player.speed.y > 0) {
            player.die()
            player.speed.y = -50F
        }
    }





}
