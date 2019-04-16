package com.mteam.collector.Controller

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.mteam.collector.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun startButtonDidTap(view: View) {
        val dataEntryActivity = Intent(this, DataEntryActivity::class.java)
        startActivity(dataEntryActivity)
    }
}
