package com.example.ageinminutes

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnDatePicker.setOnClickListener { v ->
            clickDatePicker()
        }
    }

    private fun clickDatePicker() {
        val calendar = Calendar.getInstance()
        val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())

        val e = DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
            calendar.set(Calendar.YEAR, year)
            calendar.set(Calendar.MONTH, month)
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)

            val date = "$dayOfMonth/${month + 1}/$year"
            txtSelectedDate.text = date

            val selectedDate = sdf.parse(date)
            val selectedDateInMinutes = selectedDate!!.time / 60000

            val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))
            val currentDateInMinutes = currentDate!!.time / 60000

            txtSelectedMinutes.text = (currentDateInMinutes - selectedDateInMinutes).toString()
        }

        val picker = DatePickerDialog(
            this,
            e,
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )
        // 86400000 is milliseconds of 24 hours
        // use to restrict the user to select today and future day.
        picker.datePicker.maxDate = Date().time - 86400000
        picker.show()
    }
}
