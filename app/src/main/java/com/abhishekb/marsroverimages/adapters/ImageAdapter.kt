package com.abhishekb.marsroverimages.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.abhishekb.marsroverimages.MainActivity
import com.abhishekb.marsroverimages.Photo
import com.abhishekb.marsroverimages.R
import com.abhishekb.marsroverimages.ResponseData
import com.bumptech.glide.Glide
import com.bumptech.glide.GlideContext
import com.squareup.picasso.Picasso

class ImageAdapter(val context: Context, val imageList : List<Photo>) :  RecyclerView.Adapter<ImageAdapter.MyViewHolder>(){


    private lateinit var mListener: onItemClickListener


    interface onItemClickListener{

        fun onItemClick(position: Int)


    }

    fun setOnItemClickListener(listener: onItemClickListener){
        mListener = listener
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
            val itemView = LayoutInflater.from(parent.context).inflate(R.layout.image_item, parent, false)
            return MyViewHolder(itemView,mListener)
        }

        override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

            val currentImage = imageList[position].img_src
            //holder.image.setImageResource(currentImage.img_src.toString().toInt())
            Glide.with(context)
                .load(currentImage)
                .into(holder.image);

        }

        override fun getItemCount(): Int {
            return imageList.size
        }

        class MyViewHolder(itemView: View, listener: onItemClickListener) : RecyclerView.ViewHolder(itemView) {

            val image = itemView.findViewById<ImageView>(R.id.marsimage)

            init {
                itemView.setOnClickListener{
                    listener.onItemClick(adapterPosition)
                }
            }

        }

    }