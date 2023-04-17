package com.doodlejump.Monsters

import com.doodlejump.GameObject
import com.doodlejump.Player
import com.doodlejump.Vector

class Monster(var pos0: Vector, var type : Int): GameObject(Vector(224F, 60F), pos0, type) {

    override fun whenHit(player: Player) {
        if (player.speed.y < 0) player.rebound()
        else player.die()
    }


}
