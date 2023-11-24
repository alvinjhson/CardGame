package com.example.cardgame

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import kotlin.math.round

class PlayActivity : AppCompatActivity() {

    lateinit var textViewPlayer : TextView
    lateinit var textViewDealer : TextView
    lateinit var textViewWinLose : TextView

    lateinit var playerCard1 : ImageView
    lateinit var playerCard2 : ImageView
    lateinit var playerCard3 : ImageView
    lateinit var playerCard4 : ImageView
    lateinit var playerCard5 : ImageView
    lateinit var dealerCard1 : ImageView
    lateinit var dealerCard2 : ImageView
    lateinit var dealerCard3 : ImageView
    lateinit var dealerCard4 : ImageView
    lateinit var dealerCard5 : ImageView

    var playerValue : Int = 0
    var dealerValue : Int = 0
    var nextCardIndex : Int = 0
    var nextDealerCardIndex : Int = 0
    var gameOver : Boolean = false
    var winner : Boolean = false
    var draw : Boolean = false
    var playerWins = 0
    var dealerWins = 0
    var draws = 0
    var roundsPlayed = 0

    val takenCards = mutableListOf<Pair<Int, Int>>()
    lateinit var playerCards: List<ImageView>
    lateinit var dealerCards: List<ImageView>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_play)
        textViewPlayer = findViewById(R.id.textViewPlayer)
        textViewDealer = findViewById(R.id.textViewDealer)
        textViewWinLose = findViewById(R.id.winLoseText)
        playerCard1 = findViewById(R.id.playerCard1)
        playerCard2 = findViewById(R.id.playerCard2)
        playerCard3 = findViewById(R.id.playerCard3)
        playerCard4 = findViewById(R.id.playerCard4)
        playerCard5 = findViewById(R.id.playerCard5)
        dealerCard1 = findViewById(R.id.dealerCard1)
        dealerCard2 = findViewById(R.id.dealerCard2)
        dealerCard3 = findViewById(R.id.dealerCard3)
        dealerCard4 = findViewById(R.id.dealerCard4)
        dealerCard5 = findViewById(R.id.dealerCard5)
        val hitButton = findViewById<Button>(R.id.hitButton)
        val standButton = findViewById<Button>(R.id.standButton)
        val homeButton = findViewById<Button>(R.id.homeButton2)
        val dealButton = findViewById<Button>(R.id.dealButton)
        playerCards = listOf(playerCard1, playerCard2,playerCard3,playerCard4,playerCard5)
        dealerCards = listOf(dealerCard1,dealerCard2,dealerCard3,dealerCard4,dealerCard5)
        //val takenCards = mutableListOf<Pair<Int, Int>>()
        val sharedPref = getSharedPreferences("MyApp", Context.MODE_PRIVATE)
        playerWins = sharedPref.getInt("playerWins", 0)
        dealerWins = sharedPref.getInt("dealerWins", 0)
        draws = sharedPref.getInt("draws", 0)
        roundsPlayed = sharedPref.getInt("roundsPlayed", 0)
        dealButton.setOnClickListener {
            playBlackJack()
            dealButton.visibility = View.GONE
            textViewWinLose.visibility = View.GONE
            draw = false
        }
        homeButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
    override fun onStop() {
        super.onStop()
        val sharedPref = getSharedPreferences("MyApp", Context.MODE_PRIVATE)
        with (sharedPref.edit()) {
            putInt("playerWins", playerWins)
            putInt("dealerWins",dealerWins)
            putInt("draws",draws)
            putInt("roundsPlayed",roundsPlayed)
            apply()
        }
    }
    fun playBlackJack(){
        val hitButton = findViewById<Button>(R.id.hitButton)
        val standButton = findViewById<Button>(R.id.standButton)
        firstCards()
        hitButton.setOnClickListener{
            hit()
        }
        standButton.setOnClickListener{
            giveOutDealerCard()
        }
    }
    fun checkBlackJack() {
        if (playerValue == 21 && dealerValue  > 21){
            playerWins++
            gameOver = true
            winner = true
            resetGame()
        } else if (dealerValue == 21 && playerValue > 21){
            gameOver = true
            dealerWins++
            winner = false
            resetGame()
        }
    }
    fun checkClosestTO21() {
        val playerDif = Math.abs(21 - playerValue)
        val dealerDif = Math.abs(21 - dealerValue)
        if (dealerValue < 21 ||  playerValue < 21) {
            when {
                playerDif < dealerDif -> {
                    playerWins++
                    gameOver = true
                    winner = true
                    resetGame()
                }
                dealerDif < playerDif -> {
                    dealerWins++
                    gameOver = true
                    winner = false
                    resetGame()
                }
            }
        }
    }
    fun over21() {
        if (dealerValue > 21 && playerValue < 21) {
            gameOver = true
            winner = true
            playerWins++
            resetGame()
        }  else {
            if (playerValue > 21 && dealerValue < 21) {
                gameOver = true
                winner = false
                dealerWins++
                resetGame()
            }
        }
    }
    fun checkDraw(){
        if (playerValue == dealerValue) {
            gameOver = true
            draws++
            draw = true
            resetGame()
        }
    }
    fun firstCards(){
        Handler(Looper.getMainLooper()).postDelayed({
            dealCard(playerCards[nextCardIndex])
            nextCardIndex++
        }, 500)
        Handler(Looper.getMainLooper()).postDelayed({
            dealCard(dealerCards[nextDealerCardIndex])
            nextDealerCardIndex++
        }, 1000)
        Handler(Looper.getMainLooper()).postDelayed({
            dealCard(playerCards[nextCardIndex])
            nextCardIndex++
        }, 1500)
        Handler(Looper.getMainLooper()).postDelayed({
            setCardImage(dealerCard2, Pair(20, 20))
        }, 2000)
    }
    fun giveOutDealerCard() {
        Handler(Looper.getMainLooper()).postDelayed({
            if (dealerValue < 16) {
                do {
                    if (nextDealerCardIndex < dealerCards.size) {
                        dealCard(dealerCards[nextDealerCardIndex])
                        nextDealerCardIndex++
                        over21()
                        checkBlackJack()
                    }
                } while (dealerValue < 16)
            }
            checkClosestTO21()
            checkDraw()
        },1000)

    }
    fun hit() {
        if (playerValue < 21) {
            if (nextCardIndex < playerCards.size) {
                dealCard(playerCards[nextCardIndex])
                nextCardIndex++
                over21()
            }
        }
    }
    fun dealCard(playerCard: ImageView) {
        var card: Pair<Int, Int>
        do {
            val randomTypePlayer = (0..3).random()
            val randomValuePlayer = (0..12).random()
            card = Pair(randomTypePlayer, randomValuePlayer)
        } while (card in takenCards)
        takenCards.add(card)
        setCardImage(playerCard, card)
        val value = card.second
        if (playerCard == playerCard1 || playerCard == playerCard2 || playerCard == playerCard3 || playerCard == playerCard4 || playerCard == playerCard5) {
            calculatePlayerCardValue(value)
        } else {
            calculateDealerCardValue(value)
        }
    }

    fun calculatePlayerCardValue(value: Int)  {

        playerValue = when (value) {
            0 -> playerValue + 2
            1 -> playerValue + 3
            2 -> playerValue + 4
            3 -> playerValue + 5
            4 -> playerValue + 6
            5 -> playerValue + 7
            6 -> playerValue + 8
            7 -> playerValue + 9
            8 -> playerValue + 10
            9 -> playerValue + 10
            10 -> playerValue + 10
            11 -> playerValue + 10
            12 -> playerValue + 11
            else -> playerValue
        }
        textViewPlayer.text = playerValue.toString()
    }
    fun calculateDealerCardValue(value: Int){
        dealerValue = when (value) {
            0 -> dealerValue + 2
            1 -> dealerValue + 3
            2 -> dealerValue + 4
            3 -> dealerValue + 5
            4 -> dealerValue + 6
            5 -> dealerValue + 7
            6 -> dealerValue + 8
            7 -> dealerValue + 9
            8 -> dealerValue + 10
            9 -> dealerValue + 10
            10 -> dealerValue + 10
            11 -> dealerValue + 10
            12 -> dealerValue + 11
            else -> dealerValue
        }
        textViewDealer.text = dealerValue.toString()
    }
    fun setCardImage(playerCard: ImageView, card: Pair<Int, Int>) {
        val imageResourceId = when (card) {
            Pair(0, 0) -> R.drawable._2_of_clubs
            Pair(0, 1) -> R.drawable._3_of_clubs
            Pair(0, 2) -> R.drawable._4_of_clubs
            Pair(0, 3) -> R.drawable._5_of_clubs
            Pair(0, 4) -> R.drawable._6_of_clubs
            Pair(0, 5) -> R.drawable._7_of_clubs
            Pair(0, 6) -> R.drawable._8_of_clubs
            Pair(0, 7) -> R.drawable._9_of_clubs
            Pair(0, 8) -> R.drawable._10_of_clubs
            Pair(0, 9) -> R.drawable.jack_of_clubs2
            Pair(0, 10) -> R.drawable.queen_of_clubs2
            Pair(0, 11) -> R.drawable.king_of_clubs2
            Pair(0, 12) -> R.drawable.ace_of_clubs

            Pair(1, 0) -> R.drawable._2_of_spades
            Pair(1, 1) -> R.drawable._3_of_spades
            Pair (1,2) -> R.drawable._4_of_spades
            Pair (1,3) -> R.drawable._5_of_spades
            Pair (1,4) -> R.drawable._6_of_spades
            Pair (1,5) -> R.drawable._7_of_spades
            Pair (1,6) -> R.drawable._8_of_spades
            Pair (1,7) -> R.drawable._9_of_spades
            Pair (1,8) -> R.drawable._10_of_spades
            Pair (1,9) -> R.drawable.jack_of_spades2
            Pair (1,10) -> R.drawable.queen_of_spades2
            Pair (1,11) -> R.drawable.king_of_spades2
            Pair (1,12) -> R.drawable.ace_of_spades2

            Pair(2, 0) -> R.drawable._2_of_diamonds
            Pair(2, 1) -> R.drawable._3_of_diamonds
            Pair (2,2) -> R.drawable._4_of_diamonds
            Pair (2,3) -> R.drawable._5_of_diamonds
            Pair (2,4) -> R.drawable._6_of_diamonds
            Pair (2,5) -> R.drawable._7_of_diamonds
            Pair (2,6) -> R.drawable._8_of_diamonds
            Pair (2,7) -> R.drawable._9_of_diamonds
            Pair (2,8) -> R.drawable._10_of_diamonds
            Pair (2,9) -> R.drawable.jack_of_diamonds2
            Pair (2,10) -> R.drawable.queen_of_diamonds2
            Pair (2,11) -> R.drawable.king_of_diamonds2
            Pair (2,12) -> R.drawable.ace_of_diamonds

            Pair(3, 0) -> R.drawable._2_of_hearts
            Pair(3, 1) -> R.drawable._3_of_hearts
            Pair (3,2) ->R.drawable._4_of_hearts
            Pair (3,3) ->R.drawable._5_of_hearts
            Pair (3,4) ->R.drawable._6_of_hearts
            Pair (3,5) ->R.drawable._7_of_hearts
            Pair (3,6) ->R.drawable._8_of_hearts
            Pair (3,7) ->R.drawable._9_of_hearts
            Pair (3,8) ->R.drawable._10_of_hearts
            Pair (3,9) ->R.drawable.jack_of_hearts2
            Pair (3,10) -> R.drawable.queen_of_hearts2
            Pair (3,11) -> R.drawable.king_of_hearts2
            Pair (3,12) -> R.drawable.ace_of_hearts
            Pair (20,20) -> R.drawable.screenshot_2023_11_21_at_15_57_36
            else -> R.drawable.ic_launcher_foreground
        }
        playerCard.setImageResource(imageResourceId)
    }

    fun winLoseText() {
        if (draw){
            textViewWinLose.text = "Draw"

        }
       else if (winner){
            textViewWinLose.text = "You Win"
        } else{
            textViewWinLose.text = "You Lost"
        }
    }


    fun resetGame() {

        Handler(Looper.getMainLooper()).postDelayed({
            val dealButton = findViewById<Button>(R.id.dealButton)
            winLoseText()
            textViewWinLose.visibility = View.VISIBLE
            nextDealerCardIndex = 0
            nextCardIndex = 0
            roundsPlayed++
            onStop()
            Log.d("resetGame", "resetGame called")
            for (imageView in playerCards) {
                imageView.setImageResource(0)
            }
            for (imageView in dealerCards) {
                imageView.setImageResource(0)
            }
            playerValue = 0
            dealerValue = 0
            takenCards.clear()
            dealButton.visibility = View.VISIBLE
        }, 2000)
    }
}


