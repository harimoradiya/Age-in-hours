package com.example.dobcal

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.dobcal.databinding.ActivityMainBinding
import com.google.android.material.datepicker.MaterialDatePicker
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale
import java.util.TimeZone

class MainActivity : BaseActivity() {
    val TAG = "MainActivity"

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.mbSelectDate.setOnClickListener {
            openDatePicker()
        }
    }

    //this is the MainActivity from the start

    fun openDatePicker() {
        val datePicker = MaterialDatePicker.Builder.datePicker().setTitleText("Select date")
            .setSelection(MaterialDatePicker.todayInUtcMilliseconds()).build()
        datePicker.addOnPositiveButtonClickListener {
            Log.d(TAG, "Date is selected")
            val calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"))
            calendar.time = Date(it)
            val selectedDate =
                "${calendar.get(Calendar.DAY_OF_MONTH)}/" + "${calendar.get(Calendar.MONTH) + 1}/${
                    calendar.get(
                        Calendar.YEAR
                    )
                }"
            binding.mtvYourDate.text = selectedDate
            val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
            val theDate = sdf.parse(selectedDate)
            val selectedDateInHours = theDate!!.time / 3600000
            Log.d(TAG,"Date time : ${theDate.time / 60000}")
            val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))
            val currentDateInHours = currentDate!!.time / 3600000
            Log.d(TAG,"Current date time : ${currentDate.seconds}")
            if (currentDateInHours >= selectedDateInHours) {
                val diffrenceInMintue = currentDateInHours - selectedDateInHours
                binding.mtvResult.text = diffrenceInMintue.toString()
            } else {
                Toast.makeText(this@MainActivity, "Date can not be in future!", Toast.LENGTH_SHORT).show()
            }
        }
        datePicker.show(supportFragmentManager, "tag")

    }
}

