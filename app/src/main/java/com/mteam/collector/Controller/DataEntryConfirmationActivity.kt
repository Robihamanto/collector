package com.mteam.collector.Controller

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.mteam.collector.R

class DataEntryConfirmationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_data_entry_confirmation)
    }

    fun confirmButtonDidTap (view: View) {
        val collectionActivity = Intent(this, CollectionActivity::class.java)
        startActivity(collectionActivity)
    }

}
