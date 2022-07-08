package com.example.provideomaker.activities

import android.animation.Animator
import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.provideomaker.R
import com.example.provideomaker.adapter.DropDownViewAdapter
import com.example.provideomaker.adapter.ImageAdapter
import com.example.provideomaker.adapter.ImageViewSelectedAdapter
import com.example.provideomaker.custom.GridSpacingItemDecoration
import com.example.provideomaker.model.ItemDropDown
import kotlinx.android.synthetic.main.activity_library.*
import kotlinx.android.synthetic.main.toolbar_back.*
import kotlin.math.log

class LibraryActivity : AppCompatActivity() {

    private var list = ArrayList<Int>()
    private val adapter by lazy { ImageAdapter(list) }
    private var listImageSelected = ArrayList<Int>()
    private val adapterImageSelected by lazy { ImageViewSelectedAdapter(listImageSelected) }
    private val listDropDown = ArrayList<ItemDropDown>()
    private val adapterDropDown by lazy { DropDownViewAdapter(listDropDown) }

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_library)
        setUpRecyclerView()
        var check = true
        btnBack.setOnClickListener {
            finish()
            overridePendingTransition(R.anim.left_to_right, R.anim.left_to_right_out)
        }
        btnNext.setOnClickListener {
            if (listImageSelected.size > 0) {
                startActivity(Intent(this, ExportActivity::class.java))
                overridePendingTransition(R.anim.right_to_lef_in, R.anim.right_to_left_out)
            } else {
                Toast.makeText(this, "Select 1 image pls", Toast.LENGTH_SHORT).show()
            }
        }

        iconDelete.setOnClickListener {
            listImageSelected.clear()
            adapterImageSelected.notifyDataSetChanged()
            recyclerViewImageSelected.visibility = View.GONE
            textLongPress.visibility = View.GONE
            iconDelete.visibility = View.GONE
            textViewCountImage.text = "${listImageSelected.size}/50"
            adapter.removeAllSelected()
        }

        dropDown.setOnClickListener {
            if (check) {
                recyclerDropDown.visibility = View.VISIBLE
                recyclerDropDown.alpha = 0f
                recyclerDropDown.animate()
                    .translationY(0.0f)
                    .alpha(1.0f)
                    .setListener(null)
                imageDown.animate().rotation(-180f)
            } else {
                recyclerDropDown.animate()
                    .translationY(-10f)
                    .alpha(0.0f)
                    .setListener(object : Animator.AnimatorListener {
                        override fun onAnimationStart(animation: Animator?) {
                        }

                        override fun onAnimationEnd(animation: Animator?) {
                            recyclerDropDown.visibility = View.GONE
                        }

                        override fun onAnimationCancel(animation: Animator?) {
                        }

                        override fun onAnimationRepeat(animation: Animator?) {
                        }
                    })
                imageDown.animate().rotation(0f)
            }
            check = !check
        }
    }

    private fun setUpRecyclerView() {
        list = arrayListOf<Int>(
            R.drawable.ad_image,
            R.drawable.valheim,
            R.drawable.image,
            R.drawable.ad_image,
            R.drawable.takeaphoto,
            R.drawable.ad_image,
            R.drawable.ad_image,
            R.drawable.image,
            R.drawable.ad_image,
            R.drawable.ic_light_mode_sun,
            R.drawable.ad_image,
            R.drawable.valheim,
            R.drawable.ad_image,
            R.drawable.takeaphoto,
            R.drawable.ad_image,
            R.drawable.ad_image,
            R.drawable.ad_image,
            R.drawable.image,
            R.drawable.ad_image,
            R.drawable.ad_image,
            R.drawable.valheim,
            R.drawable.ad_image,
            R.drawable.image,
            R.drawable.ad_image,
            R.drawable.ad_image,
            R.drawable.valheim,
            R.drawable.ic_light_mode_sun
        )
        adapter.setCallBack {
            if (adapter.listSelected.indexOf(it) == -1) {
                listImageSelected.add(list[it])
                adapterImageSelected.notifyItemInserted(listImageSelected.size - 1)
                if (listImageSelected.size > 0) {
                    recyclerViewImageSelected.visibility = View.VISIBLE
                    textLongPress.visibility = View.VISIBLE
                    iconDelete.visibility = View.VISIBLE
                }

                textViewCountImage.text = "${listImageSelected.size}/50"
                recyclerViewImageSelected.smoothScrollToPosition(listImageSelected.size - 1)
            } else {
                removeItem(listImageSelected.indexOf(list[it]),false)
            }
        }


        recyclerViewImage.adapter = adapter
        val spacingInPixels = resources.displayMetrics.density * 5
        recyclerViewImage.addItemDecoration(GridSpacingItemDecoration(3, spacingInPixels.toInt(), false))
        recyclerViewImage.layoutManager = GridLayoutManager(this, 3)

        adapterImageSelected.setCallBack {
            removeItem(it,true)
        }
        recyclerViewImageSelected.adapter = adapterImageSelected
        recyclerViewImageSelected.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)



        listDropDown.addAll(
            arrayOf(
                ItemDropDown(R.drawable.ad_image, "Recent", 41),
                ItemDropDown(R.drawable.image, "Skype", 42),
                ItemDropDown(R.drawable.valheim, "Facebook", 43),
                ItemDropDown(R.drawable.vertical_image, "Zalo", 44),
                ItemDropDown(R.drawable.ad_image, "Recent", 45)
            )
        )
        recyclerDropDown.adapter = adapterDropDown
        recyclerDropDown.layoutManager = LinearLayoutManager(this)

    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.left_to_right, R.anim.left_to_right_out)
    }

    private fun removeItem(it: Int,isRecyclerBottom:Boolean) {
        if(isRecyclerBottom){
            adapter.listSelected.remove(list.indexOf(listImageSelected[it]))
            adapter.notifyDataSetChanged()
        }
        listImageSelected.removeAt(it)
        adapterImageSelected.notifyItemRemoved(it)
        if (listImageSelected.size == 0) {
            recyclerViewImageSelected.visibility = View.GONE
            textLongPress.visibility = View.GONE
            iconDelete.visibility = View.GONE
        }
        textViewCountImage.text = "${listImageSelected.size}/50"
    }
}