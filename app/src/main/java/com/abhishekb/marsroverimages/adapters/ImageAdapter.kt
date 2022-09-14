package com.abhishekb.marsroverimages.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.abhishekb.marsroverimages.R
import com.squareup.picasso.Picasso

class ImageAdapter() :  RecyclerView.Adapter<ImageAdapter.MyViewHolder>(){


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

            holder.image.setImageResource(R.drawable.mars_image)

        }

        override fun getItemCount(): Int {
            return 100
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