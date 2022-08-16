package com.salaheddin65.randomnumberfirebase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class GameActivity : AppCompatActivity() {

    private lateinit var Btn1:Button
    private lateinit var Btn2:Button
    private lateinit var Btn3:Button
    private lateinit var Btn4:Button
    private lateinit var Btn5:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)
    }
}