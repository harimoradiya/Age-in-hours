package com.example.dobcal

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity


abstract class BaseActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onStart() {
        super.onStart()
        Log.d("TAG", "onStart")
    }
}