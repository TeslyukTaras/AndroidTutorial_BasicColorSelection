package com.teslyuk.android.androidtutorial.basiccolorselection

import android.app.Activity
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.SeekBar
import android.widget.TextView

class MainActivity : Activity(), SeekBar.OnSeekBarChangeListener, View.OnClickListener {

    lateinit var image: ImageView
    lateinit var barRed: SeekBar
    lateinit var barGreen: SeekBar
    lateinit var barBlue: SeekBar
    lateinit var buttonRed: Button
    lateinit var buttonOrange: Button
    lateinit var buttonYellow: Button
    lateinit var buttonGreen: Button
    lateinit var buttonBlue: Button
    lateinit var buttonIndigo: Button
    lateinit var buttonViolet: Button
    lateinit var textColor: TextView
    private var red: Int = 0
    private var green: Int = 0
    private var blue: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initView()
    }

    override fun onResume() {
        super.onResume()

        barRed.setOnSeekBarChangeListener(this)
        barGreen.setOnSeekBarChangeListener(this)
        barBlue.setOnSeekBarChangeListener(this)

        buttonRed.setOnClickListener(this)
        buttonOrange.setOnClickListener(this)
        buttonYellow.setOnClickListener(this)
        buttonGreen.setOnClickListener(this)
        buttonBlue.setOnClickListener(this)
        buttonIndigo.setOnClickListener(this)
        buttonViolet.setOnClickListener(this)

        setupImageColor()
    }

    private fun initView() {
        image = findViewById(R.id.main_image)

        barRed = findViewById(R.id.seekbar_red)
        barGreen = findViewById(R.id.seekbar_green)
        barBlue = findViewById(R.id.seekbar_blue)

        buttonRed = findViewById(R.id.main_button_red)
        buttonOrange = findViewById(R.id.main_button_orange)
        buttonYellow = findViewById(R.id.main_button_yellow)
        buttonGreen = findViewById(R.id.main_button_green)
        buttonBlue = findViewById(R.id.main_button_blue)
        buttonIndigo = findViewById(R.id.main_button_indigo)
        buttonViolet = findViewById(R.id.main_button_violet)

        textColor = findViewById<View>(R.id.main_current_color) as TextView

        red = 0
        green = 0
        blue = 0
    }

    override fun onProgressChanged(seekBar: SeekBar, i: Int, b: Boolean) {
        when (seekBar.id) {
            R.id.seekbar_red -> red = i
            R.id.seekbar_green -> green = i
            R.id.seekbar_blue -> blue = i
        }

        setupImageColor()
    }

    override fun onStartTrackingTouch(seekBar: SeekBar) {

    }

    override fun onStopTrackingTouch(seekBar: SeekBar) {

    }

    private fun setupImageColor() {
        val color = Color.argb(255, red, green, blue)
        image.setBackgroundColor(color)

        val hex = String.format("#%02x%02x%02x", red, green, blue)
        textColor.text = "$red/$green/$blue == $hex"
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.main_button_red -> {
                red = 255
                green = 0
                blue = 0
            }
            R.id.main_button_orange -> {
                red = 255
                green = 127
                blue = 0
            }
            R.id.main_button_yellow -> {
                red = 255
                green = 255
                blue = 0
            }
            R.id.main_button_green -> {
                red = 0
                green = 255
                blue = 0
            }
            R.id.main_button_blue -> {
                red = 0
                green = 0
                blue = 255
            }
            R.id.main_button_indigo -> {
                red = 75
                green = 0
                blue = 130
            }
            R.id.main_button_violet -> {
                red = 143
                green = 0
                blue = 255
            }
        }

        barRed.progress = red
        barGreen.progress = green
        barBlue.progress = blue
    }
}