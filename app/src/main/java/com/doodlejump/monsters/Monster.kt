package com.doodlejump.monsters

import android.graphics.BitmapFactory
import android.graphics.Paint
import com.doodlejump.*
import com.doodlejump.Player

open class Monster(pos0: Vector, type: Int): GameObject(Vector(217F, 144F), pos0, type){

    constructor(pos0: Vector) : this(pos0, R.drawable.monster)

    override fun whenHit(player: Player) {
        if(player.speed.y < 0 ) {
            removed = true
            player.rebound()
        } else {
            player.die()
            player.speed.y = -50F
        }
    }





}
