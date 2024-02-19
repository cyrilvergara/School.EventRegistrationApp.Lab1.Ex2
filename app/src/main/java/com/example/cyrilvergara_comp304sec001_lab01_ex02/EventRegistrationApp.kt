package com.example.cyrilvergara_comp304sec001_lab01_ex02

import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.RadioButton
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_event_registration_app)

        // Views
        val etEventName: EditText = findViewById(R.id.etEventName)
        val etEventDate: EditText = findViewById(R.id.etEventDate)
        val etEventOrganizerCompany: EditText = findViewById(R.id.etEventOrganizerCompany)
        val btnRegister: Button = findViewById(R.id.btnRegister)
        val btnReset: Button = findViewById(R.id.btnReset)
        val cbNotForProfit: CheckBox = findViewById(R.id.cbNotForProfit)
        val cbEducational: CheckBox = findViewById(R.id.cbEducational)
        val cbCorporate: CheckBox = findViewById(R.id.cbCorporate)
        val rBtnInPerson: RadioButton = findViewById(R.id.rBtnInPerson)
        val rBtnOnLine: RadioButton = findViewById(R.id.rBtnOnLine)
        val etOutput: TextView = findViewById(R.id.etOutput)
        val spinCities: Spinner = findViewById(R.id.spinCities)

        // Register Button setOnClick Listener
        btnRegister.setOnClickListener {
            // Check if at least one checkbox is checked
            if (!(cbNotForProfit.isChecked || cbEducational.isChecked || cbCorporate.isChecked)) {
                showToast("Please check at least one checkbox")
                return@setOnClickListener
            }

            // Check if a radio button is selected
            val eventType = when {
                rBtnInPerson.isChecked -> "In-Person"
                rBtnOnLine.isChecked -> "On-Line"
                else -> {
                    showToast("Please select at least one radio button")
                    return@setOnClickListener
                }
            }

            // Selected item from Spinner
            val city = spinCities.selectedItem.toString()

            // Selected checkboxes
            val registrationTypes = mutableListOf<String>()
            if (cbNotForProfit.isChecked) registrationTypes.add("Not-for-Profit")
            if (cbEducational.isChecked) registrationTypes.add("Educational")
            if (cbCorporate.isChecked) registrationTypes.add("Corporate")

            // Format the output string
            val outputFormat = getString(R.string.output_format)
            val outputText = String.format(
                outputFormat,
                etEventName.text,
                etEventDate.text,
                etEventOrganizerCompany.text,
                city,
                eventType,
                registrationTypes.joinToString(", ")
            )

            // Display formatted output
            etOutput.text = outputText

            // Display output in Toast
            val toastFormat = getString(R.string.toast_format)
            Toast.makeText(this, outputText, Toast.LENGTH_SHORT).show()

        }

        // Reset Button Click Listener
        btnReset.setOnClickListener {
            // Clear all views
            cbNotForProfit.isChecked = false
            cbEducational.isChecked = false
            cbCorporate.isChecked = false
            rBtnInPerson.isChecked = false
            rBtnOnLine.isChecked = false
            etOutput.text = ""
            etEventName.text.clear()
            etEventDate.text.clear()
            etEventOrganizerCompany.text.clear()
            spinCities.setSelection(0)
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
