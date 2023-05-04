package com.doodlejump

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.util.Log
import android.view.SurfaceHolder
import android.view.SurfaceView
import com.doodlejump.boosts.Spring
import com.doodlejump.boosts.SpringBoard
import com.doodlejump.plateforms.*
import kotlin.math.floor
import kotlin.random.Random
import com.doodlejump.monsters.*


class GameManager @JvmOverloads constructor(context: Context, attributes: AttributeSet? = null, defStyleAttr: Int = 0): SurfaceView(context, attributes,defStyleAttr),
    SurfaceHolder.Callback, Runnable  {

    private var objects = arrayListOf<GameObject>()
    private var addStack = arrayListOf<GameObject>()
    private var drawing = true;
    private var totalElapsedTime = 0.0
    private var backgroundPaint = Paint()
    private var scorePaint = Paint()
    private var genStep = 4 * Platform.size.y
    private var genBuffer = 0F
    private lateinit var thread: Thread

    var score = 0F
    var player = Player(Vector(400F, 10F))
    lateinit var canvas: Canvas

    companion object {
        const val TIME_CONSTANT = 0.5F
        const val TICK_SECOND = 50.0
        const val TICK_MS = 1000 / TICK_SECOND
        const val DENSITY = 0.7F
        const val SCORE_MULTIPLIER = 0.1F
        const val WIDTH = 1074f
        const val HEIGHT = 1584f
    }

    init {
        scorePaint.color = Color.BLACK
        scorePaint.textSize = 100F
        objects.add(BasePlatform(Vector(500F, 300F)))
       // objects.add(Jetpack(Vector(500F + Platform.size.x / 2 - SpringBoard.size.x / 2, 300F + SpringBoard.size.y)))
        objects.add(OneUsePlatform(Vector(500F, 1100F)))
        objects.add(DurationPlatform(Vector(500F, 1500F)))
        objects.add(MovingPlatform(Vector(500F, 1800F)))
        objects.add(FalsePlatform(Vector(200F, 1500F)))
        objects.add(MovingPlatform(Vector(500F, 800F)))
        objects.add(Monster(Vector(900F , 700F )))
        backgroundPaint.color = Color.WHITE
        Log.d("", "${Player.JUMP_HEIGHT}")
    }

    private fun gameLoop(elapsedTime: Double) {
        if (holder.surface.isValid) {
            canvas = holder.lockCanvas()
            canvas.drawColor( 0, PorterDuff.Mode.CLEAR );
            canvas.drawText("${score.toInt()}", 100F, 100F, scorePaint)

            var ticks = elapsedTime / TICK_MS
            objects.forEach {
                if(it is IUpdate)
                    for(i in 1..floor(ticks).toInt())
                        it.update(this)
                it.draw(this)
            }
            objects.removeAll{ it.removed }
            player.update(this)
            player.draw(this)
            player.checkCollisions(objects)

            objects.addAll(addStack); addStack.clear()
            holder.unlockCanvasAndPost(canvas)
        }
    }

    override fun run() {
        var previousFrameTime = System.currentTimeMillis()
        while (drawing) {
            val currentTime = System.currentTimeMillis()
            var elapsedTimeMS:Double=(currentTime-previousFrameTime).toDouble()
            gameLoop(elapsedTimeMS)
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
        if(player.alive) player.speed.x = deg / 10
    }

    fun changeScore(amount: Float) {
        if(player.alive) score += amount * SCORE_MULTIPLIER
    }

    fun generatePlateform(x: Float, y: Float) {
        var r = Random.nextFloat() * 100
        if(0 < r && r < 5f) addStack.add(MovingPlatform(Vector(x, y)))
        else if(5 < r && r < 10) addStack.add(OneUsePlatform(Vector(x, y)))
        else if(10 < r && r < 15) addStack.add(DurationPlatform(Vector(x, y)))
        else {
            addStack.add(BasePlatform(Vector(x, y)))
            if(15 < r && r < 20) addStack.add(Spring(Vector(x, y + Spring.size.y)))
            if(20 < r && r < 25) addStack.add(SpringBoard(Vector(x + Platform.size.x / 2 - SpringBoard.size.x / 2, y + SpringBoard.size.y)))
            if(25 < r && r < 30) addStack.add(Monster(Vector(x, y), R.drawable.movingmonster))
            if(30 < r && r < 35) addStack.add(MovingMonster(Vector(x, y)))
        }
    }

    fun moveObjects(amount: Float) {
        // Generation of the new plateforms
        genBuffer += amount
        for (i in 1..floor(genBuffer * DENSITY / (genStep)).toInt()) {
            generatePlateform(Random.nextFloat() * (WIDTH - Platform.size.x), genBuffer / i + HEIGHT)
            genBuffer -= genStep
        }

        // Moving the player up
        changeScore(amount)
        objects.forEach {
            it.move(Vector(0F, -amount))
            if (it.pos.y < 0) it.removed = true
        }
    }

    override fun surfaceCreated(p0: SurfaceHolder) {

    }

    override fun surfaceChanged(p0: SurfaceHolder, p1: Int, p2: Int, p3: Int) {

    }

    override fun surfaceDestroyed(p0: SurfaceHolder) {

    }
}