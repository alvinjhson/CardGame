package com.example.cardgame

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.graphics.Path
import android.util.DisplayMetrics
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.Animation
import android.view.animation.DecelerateInterpolator
import android.view.animation.TranslateAnimation


class PlayActivity : AppCompatActivity() {

    lateinit var textViewPlayer : TextView
    lateinit var textViewDealer : TextView
    lateinit var textViewWinLose : TextView
    lateinit var balanceTextView: TextView

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
    lateinit var betChip1 : ImageView
    lateinit var betChip2 : ImageView
    lateinit var betChip3 : ImageView
    lateinit var betChip4 : ImageView
    lateinit var betChip5 : ImageView
    lateinit var betChip6 : ImageView
    lateinit var betChip7 : ImageView
    lateinit var betChip8 : ImageView
    lateinit var betChip9 : ImageView
    lateinit var betChip10 : ImageView
    lateinit var betChip11 : ImageView
    lateinit var betChip12 : ImageView
    lateinit var betChip13 : ImageView
    lateinit var betChip14 : ImageView
    lateinit var betChip15 : ImageView
    lateinit var betChip16 : ImageView
    lateinit var betChip17 : ImageView
    lateinit var betChip18 : ImageView
    lateinit var betChip19 : ImageView
    lateinit var betChip20 : ImageView





    var playerValue : Int = 0
    var dealerValue : Int = 0
    var nextCardIndex : Int = 0
    var nextChipIndex : Int = 0
    var nextDealerCardIndex : Int = 0
    var totalChipsShown : Int = 0



    var gameOver : Boolean = false
    var winner : Boolean = false
    var draw : Boolean = false
    var playerWins = 0
    var totalChipsWon = 0
    var dealerWins = 0
    var draws = 0
    var roundsPlayed = 0
    var playerPot : Int = 0


    var chips = mutableListOf<Player>(
        Player("Alvin",3000)
    )

    val betChipsTypes = mutableListOf<Int>()



    val playerTakenCards = mutableListOf<Triple<Int, Int,Boolean>>()
    val dealerTakenCards = mutableListOf<Triple<Int, Int,Boolean>>()



    lateinit var playerCards: List<ImageView>
    lateinit var dealerCards: List<ImageView>
    lateinit var playerChips: List<ImageView>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_play)
        textViewPlayer = findViewById(R.id.textViewPlayer)
        textViewDealer = findViewById(R.id.textViewDealer)
        textViewWinLose = findViewById(R.id.winLoseText)
        balanceTextView = findViewById(R.id.balanceTextView)
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
        betChip1 = findViewById(R.id.betChip1)
        betChip2 = findViewById(R.id.betChip2)
        betChip3 = findViewById(R.id.betChip3)
        betChip4 = findViewById(R.id.betChip4)
        betChip5 = findViewById(R.id.betChip5)
        betChip6 = findViewById(R.id.betChip6)
        betChip7 = findViewById(R.id.betChip7)
        betChip8 = findViewById(R.id.betChip8)
        betChip9 = findViewById(R.id.betChip9)
        betChip10 = findViewById(R.id.betChip10)
        betChip11 = findViewById(R.id.betChip11)
        betChip12 = findViewById(R.id.betChip12)
        betChip13 = findViewById(R.id.betChip13)
        betChip14 = findViewById(R.id.betChip14)
        betChip15 = findViewById(R.id.betChip15)
        betChip16 = findViewById(R.id.betChip16)
        betChip17 = findViewById(R.id.betChip17)
        betChip18 = findViewById(R.id.betChip18)
        betChip19 = findViewById(R.id.betChip19)
        betChip20 = findViewById(R.id.betChip20)

        val hitButton = findViewById<Button>(R.id.hitButton)
        val standButton = findViewById<Button>(R.id.standButton)
        val homeButton = findViewById<Button>(R.id.homeButton2)
        val dealButton = findViewById<Button>(R.id.dealButton)
        val bet20Button = findViewById<ImageButton>(R.id.chip20Button)
        val bet50Button = findViewById<ImageButton>(R.id.chip50Button)
        val bet100Button = findViewById<ImageButton>(R.id.chip100Button)
        val doubleButton = findViewById<Button>(R.id.doubleButton)


        val btHit = AnimationUtils.loadAnimation(this,R.anim.bthit)
        val btStand = AnimationUtils.loadAnimation(this,R.anim.btstand)
        val btDeal = AnimationUtils.loadAnimation(this,R.anim.btdeal)
        val img20 = AnimationUtils.loadAnimation(this,R.anim.img20)
        val img50 = AnimationUtils.loadAnimation(this,R.anim.img50)
        val img100 = AnimationUtils.loadAnimation(this,R.anim.img100)
        val txtBalance = AnimationUtils.loadAnimation(this,R.anim.txtbalance)

        hitButton.startAnimation(btHit)
        dealButton.startAnimation(btDeal)
        standButton.startAnimation(btStand)
        bet20Button.startAnimation(img20)
        bet50Button.startAnimation(img50)
        bet100Button.startAnimation(img100)
        balanceTextView.startAnimation(txtBalance)


        playerCards = listOf(playerCard1, playerCard2,playerCard3,playerCard4,playerCard5)
        dealerCards = listOf(dealerCard1,dealerCard2,dealerCard3,dealerCard4,dealerCard5)
        playerChips = listOf(betChip1,betChip2,betChip3,betChip4,betChip5,betChip6,betChip7,betChip8
        ,betChip9,betChip10,betChip11,betChip12,betChip13,betChip14,betChip15,betChip16,betChip17,betChip18,betChip19,betChip20)

        val sharedPref = getSharedPreferences("MyApp", Context.MODE_PRIVATE)
        playerWins = sharedPref.getInt("playerWins", 0)
        dealerWins = sharedPref.getInt("dealerWins", 0)
        draws = sharedPref.getInt("draws", 0)
        roundsPlayed = sharedPref.getInt("roundsPlayed", 0)
         chips[0].balance = sharedPref.getInt("playerBalance", 0)
        totalChipsWon = sharedPref.getInt("totalChipsWon", 0)



        betChips(chips[0])
        playerBalanceTextView()
        doubleButton.visibility = View.GONE
        dealButton.setOnClickListener {
            bet20Button.setEnabled(false)
            bet50Button.setEnabled(false)
            bet100Button.setEnabled(false)
            playBlackJack()
            dealButton.visibility = View.GONE
            hitButton.setEnabled(false)
            standButton.setEnabled(false)
            doubleButton.setEnabled(false)
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
            putInt("playerBalance",chips[0].balance)
            putInt("totalChipsWon",totalChipsWon)
            apply()
        }
    }
    fun playBlackJack(){
        val hitButton = findViewById<Button>(R.id.hitButton)
        val standButton = findViewById<Button>(R.id.standButton)
        val doubleButton = findViewById<Button>(R.id.doubleButton)
        firstCards()
        hitButton.setOnClickListener{
            hit()
        }
        standButton.setOnClickListener{
            giveOutDealerCard()
            hitButton.setEnabled(false)
            standButton.setEnabled(false)
            doubleButton.setEnabled(false)
        }
        doubleButton.setOnClickListener {
            doubleBet()
            hitButton.setEnabled(false)
            standButton.setEnabled(false)
            doubleButton.setEnabled(false)

        }
    }
    fun checkBlackJack() {
        if (playerValue == 21 ){
            playerWins++
            winBet(playerPot, chips[0] )
            gameOver = true
            winner = true
            resetGame()
        } else if (dealerValue == 21 ){
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
                    winBet(playerPot, chips[0] )
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
            winBet(playerPot, chips[0] )
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
    fun dealerCardAnimation(view: ImageView) {

        val leftAnimation = ObjectAnimator.ofFloat(view, "translationX", -500f).apply {
            duration = 250
        }
        val rightAnimation = ObjectAnimator.ofFloat(view, "translationX", 50f).apply{
            duration = 250
        }

        AnimatorSet().apply {
            play(leftAnimation).with(rightAnimation)
            play(rightAnimation).with(leftAnimation)
            start()
        }
    }

    fun playerCardAnimation(view: ImageView) {

        val leftAnimation = ObjectAnimator.ofFloat(view, "translationX", -500f).apply {
            duration = 250
        }
        val rightAnimation = ObjectAnimator.ofFloat(view, "translationX", 50f).apply{
            duration = 250
        }

        AnimatorSet().apply {
            play(leftAnimation).with(rightAnimation)
            play(rightAnimation).with(leftAnimation)
            start()
        }
    }
    fun firstCards(){
        val hitButton = findViewById<Button>(R.id.hitButton)
        val standButton = findViewById<Button>(R.id.standButton)
        val doubleButton = findViewById<Button>(R.id.doubleButton)




        Handler(Looper.getMainLooper()).postDelayed({
            dealCard(playerCards[nextCardIndex])
            playerCardAnimation(playerCards[nextCardIndex])
            nextCardIndex++

       }, 500)
        Handler(Looper.getMainLooper()).postDelayed({
            dealCard(dealerCards[nextDealerCardIndex])
            dealerCardAnimation(dealerCards[nextDealerCardIndex])
            nextDealerCardIndex++
        }, 1000)
        Handler(Looper.getMainLooper()).postDelayed({
            dealCard(playerCards[nextCardIndex])
            playerCardAnimation(playerCards[nextCardIndex])
            nextCardIndex++
        }, 1500)
        Handler(Looper.getMainLooper()).postDelayed({
            setCardImage(dealerCard2, Triple(20, 20,true))
            dealerCardAnimation(dealerCards[nextDealerCardIndex])
            hitButton.setEnabled(true)
            standButton.setEnabled(true)
            doubleButton.setEnabled(true)

        }, 2000)
    }
    fun giveOutDealerCard() {
        if (playerValue > 21) {
            dealCard(dealerCards[nextDealerCardIndex])
            if (nextDealerCardIndex > 1) {
                dealerCardAnimation(dealerCards[nextDealerCardIndex])
            }
            nextDealerCardIndex++
            over21()
           checkBlackJack()
        }
        else if (dealerValue < 16 && nextDealerCardIndex < dealerCards.size) {
            dealCard(dealerCards[nextDealerCardIndex])
            if (nextDealerCardIndex > 1) {
                dealerCardAnimation(dealerCards[nextDealerCardIndex])
            }
            nextDealerCardIndex++
            over21()
            Handler(Looper.getMainLooper()).postDelayed({
                giveOutDealerCard()
                checkBlackJack()
            }, 1000)
        } else if (dealerValue < 21  && playerValue < 21) {
            checkClosestTO21()
            checkDraw()
        }
    }
    fun hit() {
        if (playerValue < 21) {
            if (nextCardIndex < playerCards.size) {
                dealCard(playerCards[nextCardIndex])
                playerCardAnimation(playerCards[nextCardIndex])
                nextCardIndex++
            }
        }
    }
    fun dealCard(playerCard: ImageView) {
        var card: Triple<Int, Int,Boolean>
        do {
            val randomTypePlayer = (0..3).random()
            val randomValuePlayer = (0..12).random()
            val usedAce = true
            card = Triple(randomTypePlayer, randomValuePlayer,usedAce)
        } while (card in playerTakenCards || card in dealerTakenCards)
        if (playerCard == playerCard1 || playerCard == playerCard2 || playerCard == playerCard3 || playerCard == playerCard4 || playerCard == playerCard5) {
            playerTakenCards.add(card)
        } else {
            dealerTakenCards.add(card)
        }
        setCardImage(playerCard, card)
        val value = card.second

        if (playerCard == playerCard1 || playerCard == playerCard2 || playerCard == playerCard3 || playerCard == playerCard4 || playerCard == playerCard5) {
            calculatePlayerCardValue(value)
        } else {
            calculateDealerCardValue(value)
        }

    }
    fun adjust1AceValue() {
        if (playerValue > 21) {
            val index = playerTakenCards.indexOfFirst { it.second == 12 && it.third == true }
            if (index != -1) {
                playerValue -= 10
                playerTakenCards[index] = Triple(
                    playerTakenCards[index].first,
                    playerTakenCards[index].second, false
                )
            }
        }
    }
   fun dealerAdjustAceValue() {
       if (dealerValue > 21) {
           val index = dealerTakenCards.indexOfFirst { it.second == 12 && it.third == true }

           if (index != -1) {
               dealerValue -= 10
               dealerTakenCards[index] = Triple(
                  dealerTakenCards[index].first,
                   dealerTakenCards[index].second, false
               )
           }
       }
   }
    fun dealerAce(): Int {
        val index = dealerTakenCards.indexOfFirst { it.second == 12 && it.third == true }
        return if (dealerValue + 11 > 21 ) {
            if (index != -1) {
                dealerTakenCards[index] = Triple(
                    dealerTakenCards[index].first,
                    dealerTakenCards[index].second, false
                )
            }
            1
        } else if (dealerValue + 11 <= 21 ) {
            11
        } else {
            0
        }
    }

    fun playerAce(): Int {
        val index = playerTakenCards.indexOfFirst { it.second == 12 && it.third == true }
        return if (playerValue + 11 > 21 ) {
            if (index != -1) {
                playerTakenCards[index] = Triple(
                    playerTakenCards[index].first,
                    playerTakenCards[index].second, false
                )
            }
            1
        } else if (playerValue + 11 <= 21 ) {
            11
        } else {
            0
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
            12 -> playerValue + playerAce()
            else -> playerValue
        }
        adjust1AceValue()
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
            12 -> dealerValue + dealerAce()
            else -> dealerValue
        }
        dealerAdjustAceValue()
        textViewDealer.text = dealerValue.toString()
    }
    fun setCardImage(playerCard: ImageView, card: Triple<Int, Int, Boolean>) {
        val imageResourceId = when (card) {
            Triple(0, 0, true) -> R.drawable._2_of_clubs
            Triple(0, 1, true) -> R.drawable._3_of_clubs
            Triple(0, 2, true) -> R.drawable._4_of_clubs
            Triple(0, 3, true) -> R.drawable._5_of_clubs
            Triple(0, 4, true) -> R.drawable._6_of_clubs
            Triple(0, 5, true) -> R.drawable._7_of_clubs
            Triple(0, 6, true) -> R.drawable._8_of_clubs
            Triple(0, 7, true) -> R.drawable._9_of_clubs
            Triple(0, 8, true) -> R.drawable._10_of_clubs
            Triple(0, 9, true) -> R.drawable.jack_of_clubs2
            Triple(0, 10, true) -> R.drawable.queen_of_clubs2
            Triple(0, 11, true) -> R.drawable.king_of_clubs2
            Triple(0, 12, true) -> R.drawable.ace_of_clubs

            Triple(1, 0, true) -> R.drawable._2_of_spades
            Triple(1, 1, true) -> R.drawable._3_of_spades
            Triple(1, 2, true) -> R.drawable._4_of_spades
            Triple(1, 3, true) -> R.drawable._5_of_spades
            Triple(1, 4, true) -> R.drawable._6_of_spades
            Triple(1, 5, true) -> R.drawable._7_of_spades
            Triple(1, 6, true) -> R.drawable._8_of_spades
            Triple(1, 7, true) -> R.drawable._9_of_spades
            Triple(1, 8, true) -> R.drawable._10_of_spades
            Triple(1, 9, true) -> R.drawable.jack_of_spades2
            Triple(1, 10, true) -> R.drawable.queen_of_spades2
            Triple(1, 11, true) -> R.drawable.king_of_spades2
            Triple(1, 12, true) -> R.drawable.ace_of_spades2

            Triple(2, 0, true) -> R.drawable._2_of_diamonds
            Triple(2, 1, true) -> R.drawable._3_of_diamonds
            Triple(2, 2, true) -> R.drawable._4_of_diamonds
            Triple(2, 3, true) -> R.drawable._5_of_diamonds
            Triple(2, 4, true) -> R.drawable._6_of_diamonds
            Triple(2, 5, true) -> R.drawable._7_of_diamonds
            Triple(2, 6, true) -> R.drawable._8_of_diamonds
            Triple(2, 7, true) -> R.drawable._9_of_diamonds
            Triple(2, 8, true) -> R.drawable._10_of_diamonds
            Triple(2, 9, true) -> R.drawable.jack_of_diamonds2
            Triple(2, 10, true) -> R.drawable.queen_of_diamonds2
            Triple(2, 11, true) -> R.drawable.king_of_diamonds2
            Triple(2, 12, true) -> R.drawable.ace_of_diamonds

            Triple(3, 0, true) -> R.drawable._2_of_hearts
            Triple(3, 1, true) -> R.drawable._3_of_hearts
            Triple(3, 2, true) -> R.drawable._4_of_hearts
            Triple(3, 3, true) -> R.drawable._5_of_hearts
            Triple(3, 4, true) -> R.drawable._6_of_hearts
            Triple(3, 5, true) -> R.drawable._7_of_hearts
            Triple(3, 6, true) -> R.drawable._8_of_hearts
            Triple(3, 7, true) -> R.drawable._9_of_hearts
            Triple(3, 8, true) -> R.drawable._10_of_hearts
            Triple(3, 9, true) -> R.drawable.jack_of_hearts2
            Triple(3, 10, true) -> R.drawable.queen_of_hearts2
            Triple(3, 11, true) -> R.drawable.king_of_hearts2
            Triple(3, 12, true) -> R.drawable.ace_of_hearts
            Triple(20, 20, true) -> R.drawable.screenshot_2023_11_21_at_15_57_36
            else -> R.drawable.ic_launcher_foreground
        }




        playerCard.setImageResource(imageResourceId)

    }

    fun showChips(chip : ImageView,value: Int){
        val drawableId = when (value) {
            0 -> R.drawable.green20
            1 -> R.drawable.red50
            2 -> R.drawable.black100

            else -> R.drawable.ic_launcher_foreground
        }
        chip.setImageResource(drawableId)
    }

    fun doubleBet() {
            hit()
            chips[0].balance -= playerPot
            playerPot = playerPot * 2
            playerBalanceTextView()

        for (chipType in betChipsTypes) {
            if (totalChipsShown < 20) {
                showChips(playerChips[totalChipsShown], chipType)
                totalChipsShown++
                Log.d("!!!","${betChipsTypes}")
            } else {
                break
            }
        }
            Handler(Looper.getMainLooper()).postDelayed({
                giveOutDealerCard()
            }, 1500)
    }
    fun betChips(player: Player) {
        val bet20Button = findViewById<ImageButton>(R.id.chip20Button)
        val bet50Button = findViewById<ImageButton>(R.id.chip50Button)
        val bet100Button = findViewById<ImageButton>(R.id.chip100Button)
        val doubleButton = findViewById<Button>(R.id.doubleButton)

        if (chips[0].balance > 20) {
            bet20Button.setOnClickListener {
                doubleButton.visibility = View.VISIBLE
                player.balance -= 20
                playerPot += 20
                playerBalanceTextView()
                if (nextChipIndex < playerChips.size) {
                    showChips(playerChips[nextChipIndex], 0)
                    nextChipIndex++
                    totalChipsShown++
                    betChipsTypes.add(0)
                }
            }
        }
        if (chips[0].balance > 50) {
            bet50Button.setOnClickListener {
                doubleButton.visibility = View.VISIBLE
                player.balance -= 50
                playerPot += 50
                playerBalanceTextView()
                if (nextChipIndex < playerChips.size) {
                    showChips(playerChips[nextChipIndex], 1)
                    nextChipIndex++
                    totalChipsShown++
                    betChipsTypes.add(1)
                }
            }
        }
        if (chips[0].balance > 100) {
            bet100Button.setOnClickListener {
                doubleButton.visibility = View.VISIBLE
                player.balance -= 100
                playerPot += 100
                playerBalanceTextView()
                if (nextChipIndex < playerChips.size) {
                    showChips(playerChips[nextChipIndex], 2)
                    nextChipIndex++
                    totalChipsShown++
                    betChipsTypes.add(2)
                }
            }
        }
    }

    fun winBet(bet : Int,player: Player) {
        var win = bet * 2
        player.balance += win
        totalChipsWon += win
    }

    fun winLoseText() {
        if (draw){
            textViewWinLose.text = "Draw"
        }
       else if (winner){
            textViewWinLose.text = "You Win"
        } else{
            textViewWinLose.text = "You Lose"
        }
    }

    fun playerBalanceTextView() {
        balanceTextView.text = "${chips[0].balance}"
    }
    fun resetGame() {
        val bet20Button = findViewById<ImageButton>(R.id.chip20Button)
        val bet50Button = findViewById<ImageButton>(R.id.chip50Button)
        val bet100Button = findViewById<ImageButton>(R.id.chip100Button)
        val doubleButton = findViewById<Button>(R.id.doubleButton)

        Handler(Looper.getMainLooper()).postDelayed({
            val dealButton = findViewById<Button>(R.id.dealButton)
            winLoseText()
            textViewWinLose.visibility = View.VISIBLE
            nextDealerCardIndex = 0
            nextCardIndex = 0
            nextChipIndex = 0
            totalChipsShown = 0
            roundsPlayed++
            onStop()
            Log.d("resetGame", "resetGame called")
            for (imageView in playerCards) {
                imageView.setImageResource(0)
            }
            for (imageView in dealerCards) {
                imageView.setImageResource(0)
            }
            for (imageView in playerChips) {
                imageView.setImageResource(0)
            }
            playerValue = 0
            dealerValue = 0
            playerPot = 0
            bet20Button.setEnabled(true)
            bet50Button.setEnabled(true)
            bet100Button.setEnabled(true)
            Log.d("!!!", "${chips[0].balance} ")
            Log.d("!!!", "$playerWins ")
            Log.d("!!!", "$dealerWins")

            playerTakenCards.clear()
            dealerTakenCards.clear()
            betChipsTypes.clear()
            playerBalanceTextView()
            dealButton.visibility = View.VISIBLE
            doubleButton.visibility = View.GONE
        }, 2000)
    }
}


