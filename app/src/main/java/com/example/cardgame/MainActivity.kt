package com.example.cardgame

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val playButton = findViewById<Button>(R.id.playButton)
        val statsButton = findViewById<Button>(R.id.statsButton)
        val sharedPref = getSharedPreferences("MyApp", Context.MODE_PRIVATE)
        val playerWins = sharedPref.getInt("playerWins", 0) // 0 är ett standardvärde
        val dealerWins = sharedPref.getInt("dealerWins", 0)
        val draws = sharedPref.getInt("draws", 0)
        val roundsPlayed = sharedPref.getInt("roundsPlayed", 0)
        val playerBalance = sharedPref.getInt("playerBalance", 0)
        val totalChipsWon = sharedPref.getInt("totalChipsWon", 0)

        playButton.setOnClickListener{
            val intent = Intent(this,PlayActivity::class.java)
            startActivity(intent)
        }
        statsButton.setOnClickListener{
            val intent = Intent (this,StatisticActivity2::class.java)
            intent.putExtra("playerWins", playerWins)
            intent.putExtra("dealerWins", dealerWins)
            intent.putExtra("draws", draws)
            intent.putExtra("roundsPlayed", roundsPlayed)
            intent.putExtra("playerBalance",playerBalance)
            intent.putExtra("totalChipsWon",totalChipsWon)
            startActivity(intent)
        }
    }
}