package com.abhishekb.marsroverimages

import android.app.DatePickerDialog
import android.app.Dialog
import android.content.Intent
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.abhishekb.marsroverimages.adapters.ImageAdapter
import com.airbnb.lottie.LottieAnimationView
import com.google.gson.Gson
import org.json.JSONObject
import org.w3c.dom.Text
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import java.io.BufferedReader
import java.io.DataOutputStream
import java.io.IOException
import java.io.InputStreamReader
import java.lang.Exception
import java.net.HttpURLConnection
import java.net.SocketTimeoutException
import java.net.URL
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var imageRV: RecyclerView
    private lateinit var selectDate: ImageView
    private  var date: String = "2022-09-09"
    private lateinit var adapter :ImageAdapter
    private lateinit var  Base_Url : String

    private var API_KEY = "RpqOHBaIN5ndzcwSwmBQmZLUSyP3bjU4DJXbGr5f"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.hide()

        val searchIcon = findViewById<LottieAnimationView>(R.id.searchIcon)
        val textV = findViewById<TextView>(R.id.textView2)
        val datetV = findViewById<TextView>(R.id.dateTv)



        date = "2022-09-09"

        imageRV = findViewById(R.id.imagesRv)
        imageRV.visibility = GONE
        datetV.visibility = GONE
        imageRV.layoutManager = GridLayoutManager(this, 3)




        selectDate = findViewById(R.id.imageView2)

        selectDate.setOnClickListener {
            val cal = Calendar.getInstance()
            val datePicker = DatePickerDialog.OnDateSetListener { datePicker, year, month, day ->
                cal.set(Calendar.YEAR, year)
                cal.set(Calendar.MONTH, month)
                cal.set(Calendar.DAY_OF_MONTH, day)
                val sdf = SimpleDateFormat("yyyy-MM-dd")
                date = sdf.format(cal.time)
                imageRV.visibility = VISIBLE
                datetV.visibility = VISIBLE
                datetV.text = "You are viewing images taken on ${date}"
                textV.visibility = GONE
                searchIcon.visibility = GONE
                Base_Url = "https://api.nasa.gov/mars-photos/api/v1/rovers/curiosity/"
                getMyData()
                //  selectDate.setText( sdf.format(cal.time))
            }
            DatePickerDialog(
                this, datePicker, cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(
                    Calendar.DAY_OF_MONTH
                )
            ).show()
        }
    }
    private fun getMyData() {
        val retrofitBuilder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(Base_Url)
            .build()
            .create(ApiInterface::class.java)

        val retrofitData = retrofitBuilder.getData()

        retrofitData.enqueue(object : Callback<ResponseData?> {

            override fun onResponse(call: Call<ResponseData?>, response: Response<ResponseData?>) {
                val responseBody = response.body()!!

                adapter = ImageAdapter(this@MainActivity,responseBody.photos)
                adapter.notifyDataSetChanged()

                imageRV.adapter = adapter

                Log.i("Api Response", responseBody.photos.toString())

                adapter.setOnItemClickListener(object : ImageAdapter.onItemClickListener {
                    override fun onItemClick(position: Int) {
                        val intent = Intent(this@MainActivity, ViewImage::class.java)
                        intent.putExtra("ImageLink", responseBody.photos[position].img_src)
                        startActivity(intent)
                    }
                })
            }
            override fun onFailure(call: Call<ResponseData?>, t: Throwable) {
                Log.i("Api Response", t.message.toString())
            }


        })
    }
    interface ApiInterface {

        @GET("photos?earth_date=2022-03-09&api_key=RpqOHBaIN5ndzcwSwmBQmZLUSyP3bjU4DJXbGr5f")
        fun getData():Call<ResponseData?>

    }
}
