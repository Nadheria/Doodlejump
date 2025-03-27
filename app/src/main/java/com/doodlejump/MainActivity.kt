package com.doodlejump

import android.content.Context
import android.content.Intent
import android.graphics.PixelFormat
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity(), SensorEventListener {

    private lateinit var game: GameManager
    private lateinit var sensor: Sensor
    private lateinit var sensorManager: SensorManager
    private lateinit var pauseMenu: View
    private lateinit var btnContinue: ImageView
    private lateinit var btnExit: ImageView
    private lateinit var scoreTv: TextView
    private var currentScore = 0

    private val sharedPreferences by lazy { getSharedPreferences("MyPrefs", Context.MODE_PRIVATE) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        game = findViewById(R.id.mainView)
        scoreTv= findViewById(R.id.scoreTextview)

        game.post {
            game.activity = this
        }

        game.setZOrderOnTop(true)
        game.holder.setFormat(PixelFormat.TRANSPARENT)

        pauseMenu = findViewById(R.id.pauseMenu)
        pauseMenu.visibility = View.GONE

        val btnPause = findViewById<ImageButton>(R.id.btnPause)
        btnContinue = findViewById(R.id.playAgain)
        btnExit = findViewById(R.id.btnExit)

        btnPause.setOnClickListener {
            game.togglePause()
        }

        btnContinue.setOnClickListener {
            game.togglePause()
        }

        btnExit.setOnClickListener {
            finish()
        }

        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION)!!
    }

    override fun onResume() {
        super.onResume()
        sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL)
        game.onResume()
    }

    override fun onPause() {
        super.onPause()
        sensorManager.unregisterListener(this)
        game.onPause()
    }

    override fun onSensorChanged(event: SensorEvent?) {
        game.setXOrientation(-(event!!.values[2] / Math.PI * 180).toFloat())
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {}

    // 🔥 Function to handle game over and save high score
    fun endScreen(score: Int) {
        val highScore = sharedPreferences.getInt("HIGH_SCORE", 0)

        if (score > highScore) {
            sharedPreferences.edit().putInt("HIGH_SCORE", score).apply() // Save new high score
        }

        Intent(baseContext, ReplayActivity::class.java).also {
            it.putExtra("SCORE", score)
            it.putExtra("HIGH_SCORE", sharedPreferences.getInt("HIGH_SCORE", 0)) // Pass high score
            startActivity(it)
        }

        finish()
    }

    fun togglePause() {
        if (game.paused) {
            pauseMenu.visibility = View.VISIBLE
            game.visibility=View.GONE
            pauseMenu.bringToFront() // 🔥 Ensure pause menu is above all elements
            pauseMenu.elevation = 100f // ⬆ Increase elevation to bring it above game elements
        } else {
            pauseMenu.visibility = View.GONE
            game.visibility=View.VISIBLE
        }
    }

    fun updateScore(score: Int) {
        currentScore = score
        runOnUiThread {
            scoreTv.text = "$score"
        }
    }

}



