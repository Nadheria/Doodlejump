package com.doodlejump.plateforms

import com.doodlejump.*
import kotlin.math.pow
import kotlin.math.sqrt

class DurationPlatform(iPos: Vector): Platform(iPos, R.drawable.durationplateform), IUpdate {

    private var obs = TimeObservable(100, this);

    override fun update(game: GameManager) {
        obs.update()
        paint.alpha = sqrt( obs.duration.toFloat() / obs.maxDuration.toFloat() * 255F.pow(2F)).toInt()
    }

    override fun whenHit(player: Player) {
        super.whenHit(player)
        obs.start()
    }

}