package com.example.provideomaker.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.provideomaker.R
import com.example.provideomaker.model.Tool

class ToolAdapter(var list: ArrayList<Tool>) : RecyclerView.Adapter<ToolAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ToolAdapter.ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.custom_tool_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ToolAdapter.ViewHolder, position: Int) {
        holder.setData()
    }

    override fun getItemCount(): Int = list.size

    inner class ViewHolder(item: View) : RecyclerView.ViewHolder(item) {
        val itemTool: CardView = item.findViewById(R.id.itemTool)
        private val imageTool: ImageView = item.findViewById(R.id.imageTool)
        private val nameTool: TextView = item.findViewById(R.id.nameTool)
        fun setData() {
            imageTool.setImageResource(list[adapterPosition].image)
            nameTool.text = list[adapterPosition].name
        }

    }
}