package com.doodlejump

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.view.SurfaceView

class GameManager @JvmOverloads constructor(context: Context, attributes: AttributeSet? = null, defStyleAttr: Int = 0): SurfaceView(context, attributes,defStyleAttr) {

    private var objects = arrayListOf<GameObject>()

    fun addObject(g: GameObject) {
        objects.add(g)
    }

    fun removeObject(g: GameObject) {
        objects.remove(g)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas?.let { objects.forEach {it.draw(canvas)} }
    }

}