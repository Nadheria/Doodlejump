package com.doodlejump

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.widget.ImageView
import kotlin.properties.Delegates

class MainActivity : AppCompatActivity() {

    private lateinit var sprite: ImageView
    private var xDown by Delegates.notNull<Float>()
    private var yDown by Delegates.notNull<Float>()

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        sprite = findViewById(R.id.character)

    }
}