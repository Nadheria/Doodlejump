package com.doodlejump

data class Vector(var x: Float, var y: Float) {

    private fun mdf(x: Float, y: Float): Vector {
        this.x = x; this.y = y;
        return this
    }

    operator fun plus(vec: Vector): Vector {
        return mdf(x + vec.x, y + vec.y)
    }

    operator fun minus(vec: Vector): Vector {
        return mdf(x - vec.x, y - vec.y)
    }

    operator fun times(a: Float): Vector {
        return mdf(x * a, y * a)
    }

    operator fun div(a: Float): Vector {
        return mdf(x / a, y / a)
    }

    operator fun get(i: Int): Float {
        return if(i == 0) x else y
    }

    operator fun set(i: Int, a: Float) {
        if(i == 0) x = a else y = a
    }
}