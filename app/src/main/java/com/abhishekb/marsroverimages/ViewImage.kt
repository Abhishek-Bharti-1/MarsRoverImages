package com.abhishekb.marsroverimages

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.squareup.picasso.Picasso

class ViewImage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_image)

        supportActionBar?.hide()

        val marsImage = findViewById<ImageView>(R.id.mainImage)
        val backBtn = findViewById<ImageView>(R.id.backBtn)

        backBtn.setOnClickListener {
            onBackPressed()
        }

        Glide.with(this).load(R.drawable.mars_image).into(marsImage)



    }
}