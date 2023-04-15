package com.doodlejump.plateforms

import com.doodlejump.*

class MovingPlateform(iPos: Vector): Platform(iPos, R.drawable.movingplateform), IUpdate {

    // Force to a byte because it is the smallest type available
    private var direction : Byte = 1

    companion object {
        const val PLATEFORM_SPEED = 5F
    }

    override fun update(game: GameManager) {
        if(pos.x > 1000F) direction = -1
        if(pos.x < 0) direction = 1

        move(pos + Vector( direction * PLATEFORM_SPEED, 0F))
    }


}