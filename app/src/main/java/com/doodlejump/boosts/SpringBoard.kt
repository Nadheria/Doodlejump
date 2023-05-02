package com.doodlejump.boosts

import com.doodlejump.GameObject
import com.doodlejump.Player
import com.doodlejump.R
import com.doodlejump.Vector

class SpringBoard(iPos: Vector): GameObject(size, iPos, R.drawable.springboard) {

    companion object {
        val size = Vector(140F, 50F)
    }

    override fun whenHit(player: Player) {
        if(player.speed.y < 0F) player.speed.y += 300F
    }
}