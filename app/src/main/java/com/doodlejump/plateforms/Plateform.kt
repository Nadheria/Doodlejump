package com.doodlejump.plateforms

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.RectF
import com.doodlejump.GameObject
import com.doodlejump.R
import com.doodlejump.Vector

abstract class Plateform(var pos0: Vector, var type : Int): GameObject(Vector(224F, 60F), pos0, type) {

    override fun isHit(box: RectF): Boolean {
        return false
    };
}