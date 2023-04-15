package com.doodlejump

import android.graphics.PixelFormat
import android.os.Bundle
import android.widget.ImageView
import android.widget.RelativeLayout
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var game: GameManager
    private lateinit var background: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        game = findViewById(R.id.mainView)
        game.setZOrderOnTop(true)
        game.holder.setFormat(PixelFormat.TRANSPARENT)
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