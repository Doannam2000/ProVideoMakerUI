package com.example.provideomaker.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import com.example.provideomaker.R
import com.example.provideomaker.adapter.ToolAdapter
import com.example.provideomaker.model.Tool
import kotlinx.android.synthetic.main.activity_export.*
import kotlinx.android.synthetic.main.toolbar_white.*

class ExportActivity : AppCompatActivity() {
    private val listTool = ArrayList<Tool>()
    private val adapterTool by lazy { ToolAdapter(listTool) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_export)

        btnBack.setOnClickListener {
            finish()
            overridePendingTransition(R.anim.left_to_right, R.anim.left_to_right_out)
        }

        listTool.addAll(
            arrayOf(
                Tool(R.drawable.ic_themes, "Themes"),
                Tool(R.drawable.ic_edit, "Edit"),
                Tool(R.drawable.ic_sticker, "Sticker"),
                Tool(R.drawable.ic_music, "Music"),
                Tool(R.drawable.ic_text, "Text"),
                Tool(R.drawable.ic_sort, "Sorting")
            )
        )

        recyclerTool.adapter = adapterTool
        recyclerTool.layoutManager = GridLayoutManager(this, 3)
        recyclerTool.setHasFixedSize(true)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.left_to_right, R.anim.left_to_right_out)
    }
}