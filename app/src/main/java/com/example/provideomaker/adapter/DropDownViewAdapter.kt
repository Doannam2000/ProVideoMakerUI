package com.example.provideomaker.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.provideomaker.R
import com.example.provideomaker.model.ItemDropDown

class DropDownViewAdapter(var list: ArrayList<ItemDropDown>) :
    RecyclerView.Adapter<DropDownViewAdapter.ViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.custom_dropdown_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setData()
    }

    override fun getItemCount(): Int = list.size

    inner class ViewHolder(item: View) : RecyclerView.ViewHolder(item) {
        private val layoutItem: LinearLayout = item.findViewById(R.id.layoutItem)
        private val imageView: ImageView = item.findViewById(R.id.image)
        private val typeImage: TextView = item.findViewById(R.id.typeImage)
        private val count: TextView = item.findViewById(R.id.countImage)
        fun setData() {
            imageView.setImageResource(list[adapterPosition].image)
            typeImage.text = list[adapterPosition].name
            count.text = list[adapterPosition].count.toString()
        }

    }
}