package com.example.cardgame

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    //val card = DeckOfCards()
    val playClass = PlayActivity()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val playButton = findViewById<Button>(R.id.playButton)
        val statsButton = findViewById<Button>(R.id.statsButton)

        playButton.setOnClickListener{

            val intent = Intent(this,PlayActivity::class.java)
            startActivity(intent)


        }




    }


}