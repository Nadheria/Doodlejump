package com.doodlejump.Monsters

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Paint
import com.doodlejump.*
import com.doodlejump.Player

class Monster(pos0: Vector): GameObject(Vector(311F, 272F), pos0, R.drawable.monster){

    companion object {
        val size = Vector(224F, 60F)

    }
    override fun whenHit(player: Player) {
        if (player.speed.y < 0) player.rebound()
        else player.speed.y = 0F
    }


}
