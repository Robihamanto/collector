package com.mteam.collector.Controller

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.MotionEvent
import android.view.View
import com.mteam.collector.Adapters.CollectionRecycleViewAdapter
import com.mteam.collector.Services.DataService
import kotlinx.android.synthetic.main.activity_collection.*
import android.hardware.SensorManager
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import com.mteam.collector.R.*


class CollectionActivity : AppCompatActivity(), SensorEventListener {

    lateinit var adapter: CollectionRecycleViewAdapter
    var collectionNumbers = DataService.getCollection()
    var counter = 0

    //Current Status
    var currentNumber  = 0

    var currentX = 0.0
    var currentY = 0.0
    var currentZ = 0.0

    var currentRawX = 0.0
    var currentRawY = 0.0

    var currentTouchPreassure = 0.0
    var currentTouchSize = 0.0

    val numberShouldPick = listOf(
        10, 48, 37, 15, 77, 30, 9, 58, 24, 14, 67, 37
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout.activity_collection)
        setupUI()
        setupList()
        setupListListener()
        setupSensor()
    }

    fun setupUI() {
        val currentNumber = numberShouldPick[counter]
        currentNumberShouldPickTextView.text = currentNumber.toString()

        //CurrentSensor
        currentSensorXTextView.text = "${currentX}"
        currentSensorYTextView.text = "${currentY}"
        currentSensorZTextView.text = "${currentZ}"

        currentSensorXRawTextView.text = "${currentRawX}"
        currentSensorYRawTextView.text = "${currentRawY}"

        currentTouchPreassureTextView.text = "${currentTouchPreassure}"
        currentTouchSizeTextView.text = "${currentTouchSize}"

    }

    fun setupList() {
        //setup for list
        adapter = CollectionRecycleViewAdapter(this, collectionNumbers) { collectNumber ->
            collectionNumberDidTap()
            adapter.notifyDataSetChanged()
            // Reset List to The Top
            numberListView.scrollToPosition(0)
        }
        numberListView.adapter = adapter

        val layoutManager = LinearLayoutManager(this)
        numberListView.layoutManager = layoutManager
        numberListView.setHasFixedSize(true)
    }

    fun setupListListener() {
        numberListView.setOnTouchListener(object : View.OnTouchListener{
            override fun onTouch(v: View?, event: MotionEvent?): Boolean {

                currentRawX = event?.getX()?.toDouble() ?: 0.0
                currentRawY = event?.getY()?.toDouble() ?: 0.0

                currentTouchPreassure = event?.getPressure()?.toDouble() ?: 0.0
                currentTouchSize = event?.getSize()?.toDouble() ?: 0.0

                //Update UI
                setupUI()

                return v?.onTouchEvent(event) ?: true
            }
        })
    }

    fun collectionNumberDidTap () {
        addCounter()
        setupUI()
        nextNumber()
    }

    fun addCounter() {
        if (counter < numberShouldPick.count()-1) {
            counter += 1
        }
    }

    fun nextNumber() {
        val currentNumberShouldPick = numberShouldPick[counter]

        for (i in 0 until collectionNumbers.count()) {
            if (this.collectionNumbers[i].number == currentNumberShouldPick) {
                this.collectionNumbers[i].isShouldSelected = true
            } else {
                this.collectionNumbers[i].isShouldSelected = false
            }
        }
    }


    private lateinit var sensorManager: SensorManager
    private var mLight: Sensor? = null

    override fun onResume() {
        super.onResume()
        mLight.also {light ->
            sensorManager.registerListener(this, light, SensorManager.SENSOR_DELAY_NORMAL)
        }
    }

    override fun onPause() {
        super.onPause()
        sensorManager.unregisterListener(this)
    }

    fun setupSensor() {

        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        mLight = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)

        // get reference of the service
        //mSensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        // focus in accelerometer
        //mAccelerometer = mSensorManager!!.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
    }


    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {

    }

    override fun onSensorChanged(event: SensorEvent?) {
        //currentSensorXTextView.text = "${event?.values}"
        currentX = event?.values?.get(0)?.toDouble() ?: 0.0
        currentY = event?.values?.get(1)?.toDouble() ?: 0.0
        currentZ = event?.values?.get(2)?.toDouble() ?: 0.0
    }

}
