package com.doodlejump.plateforms

import com.doodlejump.*

class DurationPlatform(iPos: Vector, game: GameManager): Platform(iPos, R.drawable.durationplateform) {

    private var obs = TimeObservable(100, game, this);

    override fun whenHit(player: Player) {
        super.whenHit(player)
        obs.start()
    }

}