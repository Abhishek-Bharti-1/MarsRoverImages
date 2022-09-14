package com.abhishekb.marsroverimages

import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.abhishekb.marsroverimages.adapters.ImageAdapter
import com.airbnb.lottie.LottieAnimationView
import org.w3c.dom.Text
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var imageRV : RecyclerView
    private lateinit var selectDate : ImageView

    private var API_KEY = "RpqOHBaIN5ndzcwSwmBQmZLUSyP3bjU4DJXbGr5f"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.hide()

        val searchIcon = findViewById<LottieAnimationView>(R.id.searchIcon)
        val textV = findViewById<TextView>(R.id.textView2)
        val datetV = findViewById<TextView>(R.id.dateTv)


        imageRV = findViewById(R.id.imagesRv)
        imageRV.visibility = GONE
        datetV.visibility = GONE
        imageRV.layoutManager = GridLayoutManager(this,3)
        val adapter = ImageAdapter()
        imageRV.adapter = adapter

        adapter.setOnItemClickListener(object : ImageAdapter.onItemClickListener{
            override fun onItemClick(position: Int) {
                val intent = Intent(this@MainActivity,ViewImage::class.java)
                intent.putExtra("ImageLink",R.string.ImageLink)
                startActivity(intent)

            }

        })


//        adapter.setOnItemClickListener(object : CommunityAdapter.onItemClickListener{
//            override fun onItemClick(position: Int) {
//                val intent = Intent(getActivity(),::class.java)
//                intent.putExtra("chat",heading[position])
//                getActivity()?.startActivity(intent)
//
//            }
//        }



        selectDate  =  findViewById(R.id.imageView2)

        selectDate.setOnClickListener {
            val cal = Calendar.getInstance()
            val datePicker = DatePickerDialog.OnDateSetListener { datePicker, year, month, day ->
                cal.set(Calendar.YEAR,year)
                cal.set(Calendar.MONTH,month)
                cal.set(Calendar.DAY_OF_MONTH,day)
                val sdf = SimpleDateFormat("dd-MM-yyyy")
                imageRV.visibility = VISIBLE
                datetV.visibility = VISIBLE
                datetV.text = "You are viewing images taken on ${sdf.format(cal.time)}"
                textV.visibility = GONE
                searchIcon.visibility = GONE
                //  selectDate.setText( sdf.format(cal.time))
            }
            DatePickerDialog(this,datePicker,cal.get(Calendar.YEAR),cal.get(Calendar.MONTH),cal.get(
                Calendar.DAY_OF_MONTH)).show()
        }

    }
}