package com.example.provideomaker.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatDelegate
import com.example.provideomaker.R
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.toolbar.*

class MainActivity : AppCompatActivity() {
    private val sharedReference by lazy { getSharedPreferences("night_mode", 0) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var isLightMode = sharedReference.getBoolean("check", true)
        if (isLightMode) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            switchMode.isChecked = true
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            switchMode.isChecked = false
        }
        switchMode.setOnCheckedChangeListener { _, isChecked ->
            isLightMode = isChecked
            sharedReference.edit().putBoolean("check", isLightMode).apply()
            if (isChecked) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            }
        }

        switchMode.setOnClickListener {
            motionLayout.transitionToEnd()
        }

        layoutLinear.setOnClickListener {
            startActivity(Intent(this, LibraryActivity::class.java))
            overridePendingTransition(R.anim.right_to_lef_in, R.anim.right_to_left_out)
        }
    }
}