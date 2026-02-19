package com.example.prog7313_tip_calc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import  android.widget.TextView
import android.widget.EditText
import android.widget.Toast
import android.widget.RadioButton

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //variables
        val calcTip = findViewById<Button>(R.id.button)
        val inputAmount = findViewById<EditText>(R.id.edit_text)
        //val question = findViewById<TextView>(R.id.splitQuestion)
        val finalBill = findViewById<TextView>(R.id.billReport)
        val yesButton = findViewById<RadioButton>(R.id.yesButton)
        val noButton = findViewById<RadioButton>(R.id.noButton)

        //Calculate tip
        //Calculate tip
        calcTip.setOnClickListener {
            val inputString = inputAmount.text.toString()
            val tip = 0.1

            if (inputString.isNotEmpty()) {
                val billValue = inputString.toDouble()
                if (noButton.isChecked) {
                    val tipAmount = billValue * tip
                    val finalAmount = tipAmount + billValue

                    finalBill.text = String.format(
                        "\nSplit Bill: No\nTip 1: R %.2f\nTip 2: N/A\nTotal: R %.2f",
                        tipAmount, finalAmount
                    )

                } else if (yesButton.isChecked) {
                    val splitBill = billValue * 0.5
                    val tipValue = splitBill * tip
                    val individualAmount = tipValue + splitBill
                    val finalAmount = individualAmount * 2

                    finalBill.text = String.format(
                        "\nSplit Bill: Yes\nTip 1: R %.2f\nTip 2: R %.2f\nTotal: R %.2f",
                        tipValue, tipValue, finalAmount
                    )

                } else {
                    Toast.makeText(this, "Please select whether to split the bill", Toast.LENGTH_SHORT).show()
                }

            } else {
                Toast.makeText(this, "Please enter a bill amount", Toast.LENGTH_SHORT).show()
            }
        }
    }
}

