package com.mteam.collector.Controller

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.mteam.collector.R
import kotlinx.android.synthetic.main.activity_data_entry.*

class DataEntryActivity : AppCompatActivity() {

    var isSitCheck = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_data_entry)
        updateRadioButton()
    }


    fun enterButtonDidTap (view: View) {
        val dataConfirmationActivity = Intent(this, DataEntryConfirmationActivity::class.java)
        startActivity(dataConfirmationActivity)
    }

    fun sitRadioButtonDidTap (view: View) {
        isSitCheck = true
        updateRadioButton()
    }

    fun standRadioButtonDidTap (view: View) {
        isSitCheck = false
        updateRadioButton()
    }

    fun updateRadioButton () {
        if (isSitCheck == true) {
            sitRadioButton.isChecked = true
            standRadioButton.isChecked = false
        } else {
            sitRadioButton.isChecked = false
            standRadioButton.isChecked = true
        }
    }



}
