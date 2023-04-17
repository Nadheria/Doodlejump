package com.doodlejump

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.util.Log
import android.view.SurfaceHolder
import android.view.SurfaceView
import com.doodlejump.plateforms.BasePlatform
import com.doodlejump.plateforms.MovingPlateform
import com.doodlejump.plateforms.Platform
import kotlin.math.floor
import kotlin.random.Random

class GameManager @JvmOverloads constructor(context: Context, attributes: AttributeSet? = null, defStyleAttr: Int = 0): SurfaceView(context, attributes,defStyleAttr),
    SurfaceHolder.Callback, Runnable  {

    private var objects = arrayListOf<GameObject>()
    private var removeStack = arrayListOf<GameObject>()
    private var addStack = arrayListOf<GameObject>()
    private var timeObservables = arrayListOf<TimeObservable>()
    private var drawing = true;
    private var totalElapsedTime = 0.0
    private var backgroundPaint = Paint()
    private var player = Player(Vector(400F, 10F))
    private var score = 0F
    private var scorePaint = Paint()
    private var genStep = 2 * Platform.size.y
    private var genBuffer = 0F
    private lateinit var thread: Thread

    lateinit var canvas: Canvas

    companion object {
        const val TIME_CONSTANT = 0.5F
        const val DENSITY = 0.8F
        const val SCORE_MULTIPLIER = 0.1F
        const val WIDTH = 1074f
        const val HEIGHT = 1584f
    }

    init {
        scorePaint.color = Color.RED
        scorePaint.textSize = 100F
        objects.add(BasePlatform(Vector(500F, 300F)))
        objects.add(BasePlatform(Vector(500F, 1300F)))
        objects.add(BasePlatform(Vector(500F, 1500F)))
        objects.add(MovingPlateform(Vector(500F, 800F)))
        backgroundPaint.color = Color.WHITE
        Log.d("", "${Player.JUMP_HEIGHT}")
    }

    private fun gameLoop() {
        if (holder.surface.isValid) {
            objects.forEach { if(it is IUpdate) it.update(this) }
            player.update(this)
            player.checkCollisions(objects)
            removeStack.forEach { objects.remove(it) }; removeStack.clear()
            addStack.forEach { objects.add(it) }; addStack.clear()
            timeObservables.forEach { it.update(); if(it.duration == 0) removeStack.add(it.linkedObject) }
            canvas = holder.lockCanvas()
            canvas.drawColor( 0, PorterDuff.Mode.CLEAR );
            objects.forEach { it.draw(this) }
            player.draw(this)
            canvas.drawText("${score.toInt()}", 100F, 100F, scorePaint)
            holder.unlockCanvasAndPost(canvas)
        }
    }

    override fun run() {
        var previousFrameTime = System.currentTimeMillis()
        while (drawing) {
            val currentTime = System.currentTimeMillis()
            var elapsedTimeMS:Double=(currentTime-previousFrameTime).toDouble()
            gameLoop()
            totalElapsedTime += elapsedTimeMS / 1000.0
            previousFrameTime = currentTime
        }
    }

    fun onPause() {
        drawing = false
        thread.join()
    }

    fun onResume() {
        drawing = true
        thread = Thread(this)
        thread.start()
    }

    fun setXOrientation(deg: Float) {
        player.speed.x = deg / 10
    }

    fun moveObjects(amount: Float) {
        // Generation of the new plateforms
        genBuffer += amount
        for (i in 1..floor(genBuffer * DENSITY / (genStep)).toInt()) {
            addStack.add(BasePlatform(Vector(Random.nextFloat() * WIDTH, genBuffer / i + HEIGHT)))
            genBuffer -= genStep
        }

        // Moving the player up
        score += amount * SCORE_MULTIPLIER
        objects.forEach {
            it.move(it.pos + Vector(0F, -amount))
            if (it.pos.y < 0) removeStack.add(it)
        }
    }

    override fun surfaceCreated(p0: SurfaceHolder) {

    }

    override fun surfaceChanged(p0: SurfaceHolder, p1: Int, p2: Int, p3: Int) {

    }

    override fun surfaceDestroyed(p0: SurfaceHolder) {

    }
}