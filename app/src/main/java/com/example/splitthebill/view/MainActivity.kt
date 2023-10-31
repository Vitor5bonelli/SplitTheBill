package com.example.splitthebill.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.splitthebill.R
import com.example.splitthebill.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val amb : ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(amb.root)
    }
}
