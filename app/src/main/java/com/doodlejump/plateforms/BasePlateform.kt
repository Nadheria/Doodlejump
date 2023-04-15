package com.doodlejump.plateforms

import com.doodlejump.R
import com.doodlejump.Vector

class BasePlateform(var ipos: Vector): Plateform(ipos, R.drawable.baseplateform) {
    override fun update() { }
    override fun whenHit() { }
}