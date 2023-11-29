package com.example.cardgame

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import org.w3c.dom.Text

class StatisticActivity2 : AppCompatActivity() {
    lateinit var playerWinsView : TextView
    lateinit var dealerWinsView : TextView
    lateinit var roundsPlayedWins : TextView
    lateinit var drawsView : TextView
    lateinit var playerBalanceView : TextView
    lateinit var totalChipsWonView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_statistic2)
        playerWinsView = findViewById(R.id.playerWinsView)
        dealerWinsView = findViewById(R.id.dealerWinView)
        roundsPlayedWins = findViewById(R.id.roundsPlayedView)
        drawsView = findViewById(R.id.drawView)
        playerBalanceView = findViewById(R.id.playerBalanceView)
        totalChipsWonView = findViewById(R.id.totalChipsWonView)

        val homeButton = findViewById<Button>(R.id.homeButton)
        val playerWins = intent.getIntExtra("playerWins", 0)
        val dealerWins = intent.getIntExtra("dealerWins", 0)
        val draws = intent.getIntExtra("draws", 0)
        val roundsPlayed = intent.getIntExtra("roundsPlayed", 0)
        val playerBalance = intent.getIntExtra("playerBalance",0)
        val totalChipsWon = intent.getIntExtra("totalChipsWon",0)

            showPlayerWins(playerWins)
            showdealerWins(dealerWins)
            showDraws(draws)
            showRoundsPlayed(roundsPlayed)
            showPlayerBalance(playerBalance)
            showTotalChipsWon(totalChipsWon)

            homeButton.setOnClickListener {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }
    }
    fun showPlayerWins(playerWins: Int) {
        val personalWins = playerWins.toString()
        playerWinsView.text = "Personal Wins $personalWins"
    }
    fun showdealerWins(dealerWins: Int) {
        val dealerWins = dealerWins.toString()
        dealerWinsView.text = "Dealer Wins $dealerWins"
    }
    fun showDraws(draws: Int) {
        val draws = draws.toString()
        drawsView.text = "Draws $draws"
    }
    fun showRoundsPlayed(roundsPlayed: Int) {
        val roundsPlayed = roundsPlayed.toString()
        roundsPlayedWins.text = "Rounds Played $roundsPlayed"
    }
    fun showPlayerBalance(playerBalance: Int) {
        val playerBalance = playerBalance.toString()
        playerBalanceView.text = "Balance $playerBalance"
    }
    fun showTotalChipsWon(totalChipsWon: Int) {
        val totalChipWon = totalChipsWon.toString()
        totalChipsWonView.text = "Total Chips Won $totalChipWon"
    }
}