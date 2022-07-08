package com.example.provideomaker.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.provideomaker.R

class ImageViewSelectedAdapter(var list: ArrayList<Int>) :
    RecyclerView.Adapter<ImageViewSelectedAdapter.ViewHolder>() {


    lateinit var closeImage: (position: Int) -> Unit
    fun setCallBack(click: (position: Int) -> Unit) {
        closeImage = click
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ImageViewSelectedAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.custom_recycler_image_selected, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ImageViewSelectedAdapter.ViewHolder, position: Int) {
        holder.setData()
    }

    override fun getItemCount(): Int = list.size

    inner class ViewHolder(item: View) : RecyclerView.ViewHolder(item) {
        private val btnClose: ImageView = item.findViewById(R.id.btnClose)
        private val imageView: ImageView = item.findViewById(R.id.imageViewSelected)
        fun setData() {
            imageView.setImageResource(list[adapterPosition])
        }

        init {
            btnClose.setOnClickListener {
                closeImage.invoke(adapterPosition)
            }
        }
    }
}