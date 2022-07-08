package com.example.provideomaker.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.provideomaker.R

class ImageAdapter(var list: ArrayList<Int>) : RecyclerView.Adapter<ImageAdapter.ViewHolder>() {

    val listSelected = ArrayList<Int>()
    lateinit var selectImage: (position: Int) -> Unit
    fun setCallBack(click: (position: Int) -> Unit) {
        selectImage = click
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.custom_item_recycler_view, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ImageAdapter.ViewHolder, position: Int) {
        holder.setData(position)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class ViewHolder(item: View) : RecyclerView.ViewHolder(item) {
        private val layout: ConstraintLayout = item.findViewById(R.id.layoutImageInLibrary)
        private val image: ImageView = item.findViewById(R.id.imageViewInLibrary)
        private val text: TextView = item.findViewById(R.id.count)
        fun setData(p: Int) {
            image.setImageResource(list[p])
            if (listSelected.indexOf(p) != -1) {
                text.text = (listSelected.indexOf(adapterPosition) + 1).toString()
                text.visibility = View.VISIBLE
            } else
                text.visibility = View.GONE
        }

        init {
            layout.setOnClickListener {
                selectImage.invoke(adapterPosition)
                if (listSelected.indexOf(adapterPosition) == -1) {
                    listSelected.add(adapterPosition)
                    text.text = listSelected.size.toString()
                    text.visibility = View.VISIBLE
                } else {
                    listSelected.removeAt(listSelected.indexOf(adapterPosition))
                    text.visibility = View.GONE
                    notifyDataSetChanged()
                }
            }
        }

    }

    fun removeAllSelected() {
        listSelected.clear()
        notifyDataSetChanged()
    }
}