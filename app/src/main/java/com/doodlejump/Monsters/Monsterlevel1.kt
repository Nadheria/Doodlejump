package com.doodlejump.Monsters

import android.graphics.RectF
import com.doodlejump.GameObject
import com.doodlejump.Player
import com.doodlejump.Vector

class Monsterlevel1(var pos0: Vector, var type : Int): GameObject(Vector(224F, 60F), pos0, type) {

    override fun whenHit(player: Player) {
        player.rebound()
        player.die()
    }


}
