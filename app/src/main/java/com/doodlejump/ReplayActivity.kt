package com.doodlejump

import android.content.Intent
import android.graphics.RectF
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import org.w3c.dom.Text

class ReplayActivity : AppCompatActivity(), View.OnTouchListener {

    private var score: Int = 0
    private lateinit var scoreView: TextView
    private lateinit var replayView: ImageView
    private lateinit var playAgainBtn: ImageView
    private lateinit var exitButton: ImageView
    private var playButton = RectF(108f, 508f, 364f, 648f)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_replay)
        score = intent.getIntExtra("SCORE", 0)
//        replayView = findViewById(R.id.playAgain)
//        replayView.setOnTouchListener(this)
//        scoreView = findViewById(R.id.score)
//        scoreView.text = score.toString()
//        scoreView.textSize = 50F


        val score = intent.getIntExtra("SCORE", 0)
        val highScore = intent.getIntExtra("HIGH_SCORE", 0)

        val scoreTextView: TextView = findViewById(R.id.playerScore)
        val highScoreTextView: TextView = findViewById(R.id.highScore)
        playAgainBtn  = findViewById<ImageView>(R.id.playAgain)
        exitButton  = findViewById<ImageView>(R.id.menu)

        scoreTextView.text = "Score: $score"
        highScoreTextView.text = "High Score: $highScore"

        playAgainBtn.setOnClickListener {
            playAgain()
        }

        exitButton.setOnClickListener {
            val intent = Intent(baseContext, MenuActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    override fun onTouch(p0: View?, event: MotionEvent?): Boolean {
        if(event == null) return false;
        if(event.action == MotionEvent.ACTION_DOWN && playButton.contains(event.x / replayView.width * 486, event.y / replayView.height * 864)) {
            val intent = Intent(baseContext, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
        return true;
    }

    fun playAgain(){
        val intent = Intent(baseContext, MainActivity::class.java)
        startActivity(intent)
        finish()
    }


}