package com.example.prog7313_tip_calc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View // Needed for View.VISIBLE and View.GONE
import android.widget.Button
import android.widget.TextView
import android.widget.EditText
import android.widget.Toast
import android.widget.RadioButton
import android.widget.RadioGroup // Needed for the toggle logic
import android.widget.LinearLayout // Needed for the hidden layout

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // UI Variables
        val calcTip = findViewById<Button>(R.id.button)
        val inputAmount = findViewById<EditText>(R.id.edit_text)
        val finalBill = findViewById<TextView>(R.id.billReport)

        val splitDecisionGroup = findViewById<RadioGroup>(R.id.splitDesicion)
        val yesButton = findViewById<RadioButton>(R.id.yesButton)
        val noButton = findViewById<RadioButton>(R.id.noButton)

        // New UI Variables for Splitting
        val customInputsLayout = findViewById<LinearLayout>(R.id.customInputs)
        val peopleInput = findViewById<EditText>(R.id.people_input)
        val customTipInput = findViewById<EditText>(R.id.custom_tip_input)

        // Show or Hide custom inputs based on Radio Button selection
        splitDecisionGroup.setOnCheckedChangeListener { _, checkedId ->
            if (checkedId == R.id.yesButton) {
                customInputsLayout.visibility = View.VISIBLE
            } else {
                customInputsLayout.visibility = View.GONE
            }
        }

        // Calculate tip
        calcTip.setOnClickListener {
            val inputString = inputAmount.text.toString()

            if (inputString.isNotEmpty()) {
                val billValue = inputString.toDouble()

                if (noButton.isChecked) {
                    // Standard 10% tip, no split
                    val tip = 0.10
                    val tipAmount = billValue * tip
                    val finalAmount = tipAmount + billValue

                    finalBill.text = String.format(
                        "\nSplit Bill: No\nTip 1: R %.2f\nTip 2: N/A\nTotal: R %.2f",
                        tipAmount, finalAmount
                    )

                } else if (yesButton.isChecked) {
                    // Custom tip and split calculation
                    val peopleString = peopleInput.text.toString()
                    val customTipString = customTipInput.text.toString()

                    // Ensure the user actually filled out the new fields
                    if (peopleString.isNotEmpty() && customTipString.isNotEmpty()) {
                        val numPeople = peopleString.toInt()

                        // Convert percentage (e.g., 15) to decimal (e.g., 0.15)
                        val customTipDecimal = customTipString.toDouble() / 100.0

                        // Prevent division by zero if they enter '0' people
                        if (numPeople > 0) {
                            val totalTipAmount = billValue * customTipDecimal
                            val totalBillAmount = billValue + totalTipAmount

                            val tipPerPerson = totalTipAmount / numPeople
                            val totalPerPerson = totalBillAmount / numPeople

                            finalBill.text = String.format(
                                "\nSplit Bill: Yes (%d people)\nTip per person: R %.2f\nTotal per person: R %.2f\nGrand Total: R %.2f",
                                numPeople, tipPerPerson, totalPerPerson, totalBillAmount
                            )
                        } else {
                            Toast.makeText(this, "Number of people must be at least 1", Toast.LENGTH_SHORT).show()
                        }
                    } else {
                        Toast.makeText(this, "Please enter people and tip percentage", Toast.LENGTH_SHORT).show()
                    }

                } else {
                    Toast.makeText(this, "Please select whether to split the bill", Toast.LENGTH_SHORT).show()
                }

            } else {
                Toast.makeText(this, "Please enter a bill amount", Toast.LENGTH_SHORT).show()
            }
        }
    }
}