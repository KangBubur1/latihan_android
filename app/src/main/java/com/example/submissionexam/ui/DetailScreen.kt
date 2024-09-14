package com.example.submissionexam.ui

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.submissionexam.MainActivity
import com.example.submissionexam.R

class DetailScreen : AppCompatActivity() {

    private lateinit var tvDetailName: TextView
    private lateinit var tvDetailDescription: TextView
    private lateinit var tvDetailPrice: TextView
    private lateinit var imgDetailPhoto: ImageView

    private lateinit var btnBack: ImageButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_detail_screen)

        tvDetailName = findViewById(R.id.tv_item_name)
        tvDetailDescription = findViewById(R.id.tv_item_description)
        tvDetailPrice = findViewById(R.id.tv_item_price)
        imgDetailPhoto = findViewById(R.id.img_item_photo)

        val name = intent.getStringExtra("EXTRA_NAME")
        val description = intent.getStringExtra("EXTRA_DESCRIPTION")
        val price = intent.getStringExtra("EXTRA_PRICE")
        val photo = intent.getStringExtra("EXTRA_PHOTO")

        tvDetailName.text = name
        tvDetailDescription.text = description
        tvDetailPrice.text = price

        Glide.with(this)
            .load(photo)
            .into(imgDetailPhoto)

        btnBack = findViewById(R.id.back_button)
        btnBack.setOnClickListener {
            intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }


        val btnShare: ImageButton = findViewById(R.id.share_button)
        btnShare.setOnClickListener {
            shareProduct()
        }


    }

    private fun shareProduct() {
        val btnShare = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, "Share this product to your friends")
            type = "text/plain"
        }

        val shareIntent = Intent.createChooser(btnShare, "$tvDetailName")
        startActivity(shareIntent)
    }

}