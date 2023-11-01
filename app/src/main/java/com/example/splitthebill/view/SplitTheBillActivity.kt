package com.example.splitthebill.view

import android.os.Bundle
import android.os.PersistableBundle
import com.example.splitthebill.databinding.ActivitySplitTheBillBinding


class SplitTheBillActivity : BaseActivity(){

    private val stba: ActivitySplitTheBillBinding by lazy {
        ActivitySplitTheBillBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(stba.root)
        supportActionBar?.subtitle = "Friendlist!"

    }
}