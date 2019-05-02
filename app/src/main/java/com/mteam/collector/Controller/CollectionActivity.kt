package com.mteam.collector.Controller

import android.annotation.SuppressLint
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
import android.view.Menu
import android.view.VelocityTracker
import com.mteam.collector.Model.RawData
import com.mteam.collector.R.*
import java.io.FileWriter
import java.io.IOException
import android.os.Handler
import android.os.SystemClock




class CollectionActivity : AppCompatActivity(), SensorEventListener {

    lateinit var adapter: CollectionRecycleViewAdapter
    var collectionNumbers = DataService.getCollection()
    var counter = 0

    //Current Status
    var currentNumber = 1
    var currentSession = 1
    var currentFlick = 1

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
        setupContentView()
        setupUI()
        setupList()
        setupListListener()
        setupSensor()
    }

    fun setupContentView() {
        setContentView(layout.activity_collection)
        setSupportActionBar(findViewById(com.mteam.collector.R.id.toolbar))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    fun simulateTouch() {

        println("simulate done")
    }

    fun singleTouch() {
        // Obtain MotionEvent object
        val downTime = SystemClock.uptimeMillis()
        val eventTime = SystemClock.uptimeMillis() + 1000
        val x = 600f
        val y = 1200f
        // List of meta states found here: developer.android.com/reference/android/view/KeyEvent.html#getMetaState()
        val metaState = 0
        val motionEvent = MotionEvent.obtain(
            downTime,
            eventTime,
            MotionEvent.ACTION_DOWN,
            x,
            y,
            metaState
        )
    }

    @SuppressLint("ResourceType")
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(com.mteam.collector.R.layout.collection_menu, menu)
        return super.onCreateOptionsMenu(menu)
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
        numberListView.setOnTouchListener(object : View.OnTouchListener {
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

    private val DEBUG_TAG = "Velocity"
    private var mVelocityTracker: VelocityTracker? = null


    override fun dispatchTouchEvent(event: MotionEvent?): Boolean {
        val evenAction = event?.action

        currentRawX = event?.getX()?.toDouble() ?: 0.0
        currentRawY = event?.getY()?.toDouble() ?: 0.0

        currentTouchPreassure = event?.getPressure()?.toDouble() ?: 0.0
        currentTouchSize = event?.getSize()?.toDouble() ?: 0.0

        println("user did tap here X: ${currentRawX} Y: ${currentRawY} cui ")


        when (evenAction) {
            MotionEvent.ACTION_DOWN -> {
                // Reset the velocity tracker back to its initial state.
                mVelocityTracker?.clear()
                // If necessary retrieve a new VelocityTracker object to watch the
                // velocity of a motion.
                mVelocityTracker = mVelocityTracker ?: VelocityTracker.obtain()
                // Add a user's movement to the tracker.
                mVelocityTracker?.addMovement(event)
            }

            MotionEvent.ACTION_MOVE -> {
                mVelocityTracker?.apply {
                    val pointerId: Int = event.getPointerId(event.actionIndex)
                    addMovement(event)
                    // When you want to determine the velocity, call
                    // computeCurrentVelocity(). Then call getXVelocity()
                    // and getYVelocity() to retrieve the velocity for each pointer ID.
                    computeCurrentVelocity(1000)
                    // Log velocity of pixels per second
                    // Best practice to use VelocityTrackerCompat where possible.

                    //println("${getXVelocity(pointerId)}")
                    //println("${getYVelocity(pointerId)}")

                }
            }
            MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> {

                //Append Data to RawData
                val data = RawData(
                    currentX,
                    currentY,
                    currentZ,
                    currentRawX,
                    currentRawY,
                    currentTouchPreassure,
                    currentTouchSize
                )

                rawData += data

                // Return a VelocityTracker object back to be re-used by others.
                mVelocityTracker?.recycle()
                mVelocityTracker = null
            }
        }

        return super.dispatchTouchEvent(event)
    }


    fun collectionNumberDidTap() {
        addCounter()
        setupUI()
        nextNumber()
        printRawData()
    }

    fun addCounter() {
        if (counter < numberShouldPick.count() - 1) {
            counter += 1
        }
    }

    fun handleSession() {
        currentNumber += 1

        if (currentNumber == 40) {
            currentNumber = 0
            currentSession += 1
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
        Handler().postDelayed({
            simulateTouch()
            setupUI()
        }, 3000)

        mLight.also { light ->
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

    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {

    }

    override fun onSensorChanged(event: SensorEvent?) {
        //currentSensorXTextView.text = "${event?.values}"
        currentX = event?.values?.get(0)?.toDouble() ?: 0.0
        currentY = event?.values?.get(1)?.toDouble() ?: 0.0
        currentZ = event?.values?.get(2)?.toDouble() ?: 0.0


    }

    var rawData = listOf<RawData>()


    fun printRawData() {
        println("Printing Raw Data")
        for (data in rawData) {
            println(data)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        //writeCsvFile()
    }


    fun writeCsvFile() {

        val CSV_HEADER = "Pitch,Roll,Azimuth,RawX,RawY,TouchPreassure,TouchSize"

        var fileWriter: FileWriter? = null

        fileWriter = FileWriter("collectorRawData.csv")

        try {
            println("Writ 1")

            println("Writ 2")
            fileWriter.append(CSV_HEADER)
            println("Writ 3")
            fileWriter.append('\n')
            println("Writ 4")
            for (data in rawData) {
                println("Writ ${data.pitch}, ${data.roll}, ${data.azimuth}, ${data.rawX}, ${data.rawX}, ${data.rawY}, ${data.touchPreassure}, ${data.touchSize}")
                fileWriter.append("${data.pitch}")
                fileWriter.append(',')
                fileWriter.append("${data.roll}")
                fileWriter.append(',')
                fileWriter.append("${data.azimuth}")
                fileWriter.append(',')
                fileWriter.append("${data.rawX}")
                fileWriter.append(',')
                fileWriter.append("${data.rawY}")
                fileWriter.append(',')
                fileWriter.append("${data.touchPreassure}")
                fileWriter.append(',')
                fileWriter.append("${data.touchSize}")
                fileWriter.append('\n')
            }
            println("Write CSV successfully!")
        } catch (e: Exception) {
            println("Writing CSV error!")
            e.printStackTrace()
        } finally {
            try {
                fileWriter?.flush()
                fileWriter?.close()
            } catch (e: IOException) {
                println("Flushing/closing error!")
                e.printStackTrace()
            }
        }

    }
}
