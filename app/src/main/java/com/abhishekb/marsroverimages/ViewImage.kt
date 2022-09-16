package com.abhishekb.marsroverimages

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.bumptech.glide.Glide.with
import com.squareup.picasso.Picasso

class ViewImage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_image)

        supportActionBar?.hide()

        val marsImage = findViewById<ImageView>(R.id.mainImage)
        val backBtn = findViewById<ImageView>(R.id.backBtn)
        val image = intent.getStringExtra("ImageLink")
        //Toast.makeText(this,"$image",Toast.LENGTH_LONG).show()

        backBtn.setOnClickListener {
            onBackPressed()
        }

        Picasso.get().load(image).fit().into(marsImage);



    }
}