package com.example.presensi_project

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.DatePicker.OnDateChangedListener
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.example.presensi_project.databinding.ActivityMainBinding
import java.util.Calendar


class MainActivity : AppCompatActivity() {
//    private val binding by lazy { Activit }
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        with(binding){
//            Get Array
            val monthList = resources.getStringArray(R.array.month)

//            Initiate
            var selectedTime ="${timePicker.hour}:${timePicker.minute}"
            val _tempCalendar : Calendar = Calendar.getInstance()
            _tempCalendar.timeInMillis = System.currentTimeMillis()
            var selectedDate = "${_tempCalendar.get(Calendar.DAY_OF_MONTH)} ${monthList[_tempCalendar.get(Calendar.MONTH)]} ${_tempCalendar.get(Calendar.YEAR)}"

            submitButton.setOnClickListener {
                Toast.makeText(this@MainActivity, "Presensi berhasil $selectedDate jam $selectedTime", Toast.LENGTH_SHORT).show()
            }

//            Kehadiran Dropdown=======================================
            val kehadiranList = resources.getStringArray(R.array.kehadiranList)
            val adapterKehadiran = ArrayAdapter(this@MainActivity, android.R.layout.simple_spinner_item, kehadiranList)
            kehadiranSpinner.adapter = adapterKehadiran

//            Selected Kehadiran
            kehadiranSpinner.onItemSelectedListener =
                object : AdapterView.OnItemSelectedListener {
                    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                        when (kehadiranList[position]) {
                            "Tepat Waktu" -> {
                                keteranganEdittext.visibility = View.GONE
                            }
                            "Terlambat" -> {
                                keteranganEdittext.visibility = View.VISIBLE
                            }
                            "Izin" -> {
                                keteranganEdittext.visibility = View.VISIBLE
                            }
                        }
                    }

                    override fun onNothingSelected(parent: AdapterView<*>?) {
                        keteranganEdittext.visibility = View.GONE
                    }
                }
            datepicker.init()


        }
    }
}