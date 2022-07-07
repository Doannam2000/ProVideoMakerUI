package com.example.provideomaker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import com.example.provideomaker.adapter.ImageAdapter
import kotlinx.android.synthetic.main.activity_library.*

class LibraryActivity : AppCompatActivity() {

    private val list = ArrayList<Int>()
    val adapter by lazy { ImageAdapter(list) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_library)

        recyclerViewImage.adapter = adapter
        recyclerViewImage.layoutManager = GridLayoutManager(this,3)

    }
}