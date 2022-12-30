package br.com.tudolinux.dobcalc

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import java.text.SimpleDateFormat
import java.util.Locale

class MainActivity : AppCompatActivity() {
    private var selectedDateLbl : TextView? = null
    private var ageInMinutesLbl : TextView? = null

        override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnDatePicker : Button = findViewById(R.id.selectDateBtn)
            selectedDateLbl = findViewById(R.id.selectedDateLbl)
            ageInMinutesLbl = findViewById(R.id.ageInMinutesLbl)

        btnDatePicker.setOnClickListener {
            clickDatePicker()
        }
    }

    private fun clickDatePicker(){
//        val myCalendar = Calendar.getInstance()
//        val year = myCalendar.get(Calendar.YEAR)
//        val month = myCalendar.get(Calendar.MONTH)
//        val day = myCalendar.get(Calendar.DAY_OF_MONTH)
        var year = 2022
        var month = 11
        var day = 29
        val dpd = DatePickerDialog(this,
            { _, selectedYear, selectedMonth, dayOfMonth ->
                day = dayOfMonth
                month = selectedMonth
                year = selectedYear

                val selectedDate = "" +
                        "${dayOfMonth.toString().padStart(2,'0')}/" +
                        "${(selectedMonth + 1).toString().padStart(2,'0')}/" +
                        "$selectedYear"
                selectedDateLbl?.text  = selectedDate

                val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
                val theDate = sdf.parse(selectedDate)

                theDate?.let{
                    val selectedDateInMinutes = theDate.time / 60000
                    val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))
                    currentDate?.let{
                        val currentDateInMinutes = currentDate.time / 60000
                        val differenceInMinutes = currentDateInMinutes - selectedDateInMinutes
                        ageInMinutesLbl?.text = differenceInMinutes.toString()
                    }
                }
            },
            year,
            month,
            day
            )

        dpd.datePicker.maxDate = System.currentTimeMillis() - 86400000
        dpd.show()

    }
}