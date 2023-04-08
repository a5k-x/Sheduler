package com.a5k.tasksheduler.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.a5k.tasksheduler.R
import com.a5k.tasksheduler.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    var binding: ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)
    }
}