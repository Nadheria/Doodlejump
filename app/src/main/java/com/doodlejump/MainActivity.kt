package com.doodlejump

import android.annotation.SuppressLint
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.widget.ImageView
import kotlin.properties.Delegates

class MainActivity : AppCompatActivity() {

    private lateinit var game: GameManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        game = findViewById(R.id.mainView)
        game.invalidate()
    }

    override fun onPause() {
        super.onPause()
        game.onPause()
    }

    override fun onResume() {
        super.onResume()
        game.onResume()
    }
}