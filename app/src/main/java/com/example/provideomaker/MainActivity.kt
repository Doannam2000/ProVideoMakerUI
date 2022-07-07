package com.example.provideomaker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import androidx.appcompat.app.AppCompatDelegate
import kotlinx.android.synthetic.main.toolbar.*

class MainActivity : AppCompatActivity() {
    val sharedReference by lazy { getSharedPreferences("night_mode", 0) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var isLightMode = sharedReference.getBoolean("check", true)
        if (isLightMode)
            changeLightMode()
        else
            changeNightMode()
        change_mode.setOnClickListener {
            if (isLightMode) {
                changeNightMode()
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            } else {
                changeLightMode()
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
            isLightMode = !isLightMode
            sharedReference.edit().putBoolean("check", isLightMode).commit()
        }
    }

    private fun changeNightMode() {
        back_ground_switch_mode.setImageResource(R.drawable.ic_rectangle_dark_mode)
        icon_switch_mode.setImageResource(R.drawable.ic_dark_moon)
    }

    private fun changeLightMode() {
        back_ground_switch_mode.setImageResource(R.drawable.ic_rectangle_light_mode)
        icon_switch_mode.setImageResource(R.drawable.ic_light_mode_sun)

    }
}