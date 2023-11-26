package com.example.cardgame

import android.R.attr.value
import android.widget.ImageView


data class DeckOfCards(val type: Int, val value: Int,var isUsed:Boolean) {
    //private val type : Int = 0
    //private val value : Int = 1
    private val cardSymbol = arrayOf("Clubs", "Spades", "Diamonds", "Hearts")
    private val cardRank = arrayOf("2", "3", "4", "5", "6", "7", "8", "9", "Jack", "Queen", "king", "Ace")
    private val taken = arrayOf("0","1")


    override fun toString(): String {
        return cardRank[value] + " of " + cardSymbol[type]
    }
}
