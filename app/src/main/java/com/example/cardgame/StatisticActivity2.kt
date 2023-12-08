package com.example.cardgame

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.PackageManagerCompat
import org.w3c.dom.Text
import android.Manifest
import android.graphics.BitmapFactory
import android.opengl.Visibility
import android.widget.EditText
import java.io.File
import java.io.FileOutputStream


class StatisticActivity2 : AppCompatActivity() {

    companion object {
        val IMAGE_REQUEST_CODE = 1_000;
    }
    lateinit var playerWinsView : TextView
    lateinit var dealerWinsView : TextView
    lateinit var roundsPlayedWins : TextView
    lateinit var drawsView : TextView
    lateinit var playerBalanceView : TextView
    lateinit var totalChipsWonView: TextView
    lateinit var playerNameView : TextView
    lateinit var setPlayerView : ImageView
    lateinit var playerEditTextView: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_statistic2)
        setPlayerView = findViewById(R.id.setPlayerImageView)
        playerWinsView = findViewById(R.id.playerWinsView)
        dealerWinsView = findViewById(R.id.dealerWinView)
        roundsPlayedWins = findViewById(R.id.roundsPlayedView)
        drawsView = findViewById(R.id.drawView)
        playerBalanceView = findViewById(R.id.playerBalanceView)
        totalChipsWonView = findViewById(R.id.totalChipsWonView)
        playerNameView = findViewById(R.id.playerTextView)
        playerEditTextView = findViewById(R.id.editTextText)

        val sharedPreferences = getSharedPreferences("MyPreferences", Context.MODE_PRIVATE)
        val imagePath = sharedPreferences.getString("ImagePath", null)
        if (imagePath != null) {
            val imageFile = File(imagePath)
            if (imageFile.exists()) {
                val bitmap = BitmapFactory.decodeFile(imagePath)
                setPlayerView.setImageBitmap(bitmap)
            }
        }
        val sharedPref = getSharedPreferences("MyApp", Context.MODE_PRIVATE)
        playerNameView.text = sharedPref.getString("playerName", "name")

        val homeButton = findViewById<ImageButton>(R.id.homeButton)
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

        playerEditTextView.visibility = View.GONE
       playerNameView.setOnClickListener {
           playerNameView.visibility = View.GONE
           playerEditTextView.visibility = View.VISIBLE
           playerEditTextView.setOnClickListener {
               setPlayerName()
           }
        }
        setPlayerView.setOnClickListener {
            pickImageFromGallery()
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
            putString("playerName", playerNameView.text.toString())
            apply()
        }
    }

    private fun pickImageFromGallery() {
        val intent = Intent(Intent.ACTION_PICK)
       intent.type = "image/*"
        startActivityForResult(intent, IMAGE_REQUEST_CODE)
    }

  override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
      super.onActivityResult(requestCode, resultCode, data)
      if (requestCode == IMAGE_REQUEST_CODE && resultCode == RESULT_OK) {
          val imageUri = data?.data
          val bitmap = MediaStore.Images.Media.getBitmap(this.contentResolver, imageUri)
          val file = File(filesDir, "myImage.jpg")
          val out = FileOutputStream(file)
          bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out)
          out.flush()
          out.close()
          val sharedPreferences = getSharedPreferences("MyPreferences", Context.MODE_PRIVATE)
          val editor = sharedPreferences.edit()
          editor.putString("ImagePath", file.absolutePath)
          editor.apply()
          setPlayerView.setImageBitmap(bitmap)
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
        playerBalanceView.text = " $playerBalance $"
    }
    fun showTotalChipsWon(totalChipsWon: Int) {
        val totalChipWon = totalChipsWon.toString()
        totalChipsWonView.text = "Total Chips Won $totalChipWon"
    }
    fun setPlayerName (){
        var name = playerEditTextView.text.toString()
        playerNameView.text = "$name"
        playerNameView.visibility = View.VISIBLE
        playerEditTextView.visibility = View.GONE
    }
}