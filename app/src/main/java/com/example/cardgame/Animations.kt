package com.example.cardgame

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.widget.ImageView

class Animations {


    fun cardAnimation(view: ImageView) {
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
    /*
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
    */



}