package com.example.cardgame

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView

class PlayActivity : AppCompatActivity() {

    lateinit var textView : TextView

    lateinit var playerCard1 : ImageView
    lateinit var playerCard2 : ImageView
     lateinit var dealerCard1 : ImageView
     lateinit var dealerCard2 : ImageView
    val card = DeckOfCards(1,1)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_play)
        textView = findViewById(R.id.textView)
        playerCard1 = findViewById(R.id.playerCard1)
        playerCard2 = findViewById(R.id.playerCard2)
        dealerCard1 = findViewById(R.id.dealerCard1)
        dealerCard2 = findViewById(R.id.dealerCard2)




        //showCard()

        setCardImage(playerCard1,1,2)
        setCardImage(playerCard2,0,0)
        setCardImage(dealerCard1,1,2)
        setCardImage(dealerCard2,0,0)
    }


    fun setCardImage(playerCard: ImageView, type: Int, value: Int) {
        val imageResourceId = when (Pair(type, value)) {
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
            else -> R.drawable.ic_launcher_foreground // Byt ut mot standardbild eller hantera felaktiga värden på lämpligt sätt
        }

        playerCard.setImageResource(imageResourceId)
    }




























































     fun getPlayerCard2(type: Int, value: Int) {
         when (Pair(type,value)) {
             Pair (0,0) -> playerCard2.setImageResource(R.drawable._2_of_clubs)
             Pair (0,1) -> playerCard2.setImageResource(R.drawable._3_of_clubs)
             Pair (0,2) -> playerCard2.setImageResource(R.drawable._4_of_clubs)
             Pair (0,3) -> playerCard2.setImageResource(R.drawable._5_of_clubs)
             Pair (0,4) -> playerCard2.setImageResource(R.drawable._6_of_clubs)
             Pair (0,5) -> playerCard2.setImageResource(R.drawable._7_of_clubs)
             Pair (0,6) -> playerCard2.setImageResource(R.drawable._8_of_clubs)
             Pair (0,7) -> playerCard2.setImageResource(R.drawable._9_of_clubs)
             Pair (0,8) -> playerCard2.setImageResource(R.drawable._10_of_clubs)
             Pair (0,9) -> playerCard2.setImageResource(R.drawable.jack_of_clubs2)
             Pair (0,10) -> playerCard2.setImageResource(R.drawable.queen_of_clubs2)
             Pair (0,11) -> playerCard2.setImageResource(R.drawable.king_of_clubs2)
             Pair (0,12) -> playerCard2.setImageResource(R.drawable.ace_of_clubs)

             Pair (1,0) -> playerCard2.setImageResource(R.drawable._2_of_spades)
             Pair (1,1) -> playerCard2.setImageResource(R.drawable._3_of_spades)
             Pair (1,2) -> playerCard2.setImageResource(R.drawable._4_of_spades)
             Pair (1,3) -> playerCard2.setImageResource(R.drawable._5_of_spades)
             Pair (1,4) -> playerCard2.setImageResource(R.drawable._6_of_spades)
             Pair (1,5) -> playerCard2.setImageResource(R.drawable._7_of_spades)
             Pair (1,6) -> playerCard2.setImageResource(R.drawable._8_of_spades)
             Pair (1,7) -> playerCard2.setImageResource(R.drawable._9_of_spades)
             Pair (1,8) -> playerCard2.setImageResource(R.drawable._10_of_spades)
             Pair (1,9) -> playerCard2.setImageResource(R.drawable.jack_of_spades2)
             Pair (1,10) -> playerCard2.setImageResource(R.drawable.queen_of_spades2)
             Pair (1,11) -> playerCard2.setImageResource(R.drawable.king_of_spades2)
             Pair (1,12) -> playerCard2.setImageResource(R.drawable.ace_of_spades2)

             Pair (2,0) -> playerCard2.setImageResource(R.drawable._2_of_diamonds)
             Pair (2,1) -> playerCard2.setImageResource(R.drawable._3_of_diamonds)
             Pair (2,2) -> playerCard2.setImageResource(R.drawable._4_of_diamonds)
             Pair (2,3) -> playerCard2.setImageResource(R.drawable._5_of_diamonds)
             Pair (2,4) -> playerCard2.setImageResource(R.drawable._6_of_diamonds)
             Pair (2,5) -> playerCard2.setImageResource(R.drawable._7_of_diamonds)
             Pair (2,6) -> playerCard2.setImageResource(R.drawable._8_of_diamonds)
             Pair (2,7) -> playerCard2.setImageResource(R.drawable._9_of_diamonds)
             Pair (2,8) -> playerCard2.setImageResource(R.drawable._10_of_diamonds)
             Pair (2,9) -> playerCard2.setImageResource(R.drawable.jack_of_diamonds2)
             Pair (2,10) -> playerCard2.setImageResource(R.drawable.queen_of_diamonds2)
             Pair (2,11) -> playerCard2.setImageResource(R.drawable.king_of_diamonds2)
             Pair (2,12) -> playerCard2.setImageResource(R.drawable.ace_of_diamonds)

             Pair (3,0) -> playerCard2.setImageResource(R.drawable._2_of_hearts)
             Pair (3,1) -> playerCard2.setImageResource(R.drawable._3_of_hearts)
             Pair (3,2) -> playerCard2.setImageResource(R.drawable._4_of_hearts)
             Pair (3,3) -> playerCard2.setImageResource(R.drawable._5_of_hearts)
             Pair (3,4) -> playerCard2.setImageResource(R.drawable._6_of_hearts)
             Pair (3,5) -> playerCard2.setImageResource(R.drawable._7_of_hearts)
             Pair (3,6) -> playerCard2.setImageResource(R.drawable._8_of_hearts)
             Pair (3,7) -> playerCard2.setImageResource(R.drawable._9_of_hearts)
             Pair (3,8) -> playerCard2.setImageResource(R.drawable._10_of_hearts)
             Pair (3,9) -> playerCard2.setImageResource(R.drawable.jack_of_hearts2)
             Pair (3,10) -> playerCard2.setImageResource(R.drawable.queen_of_hearts2)
             Pair (3,11) -> playerCard2.setImageResource(R.drawable.king_of_hearts2)
             Pair (3,12) -> playerCard2.setImageResource(R.drawable.ace_of_hearts)

         }
     }




    fun showCard() {
        textView.text = card.toString()
    }
}