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
        val question = findViewById<TextView>(R.id.splitQuestion)
        val finalBill = findViewById<TextView>(R.id.billReport)
        val yesButton = findViewById<RadioButton>(R.id.yesButton)
        val noButton = findViewById<RadioButton>(R.id.noButton)

        //Calculate tip
        calcTip.setOnClickListener {
            val inputString = inputAmount.text.toString()
            val tip = 0.1;
            val billValue = inputString.toDouble()
            if (inputString.isNotEmpty() && noButton.isActivated) {
                val tipAmount = billValue * tip;
                var finalAmount = tipAmount + billValue;
                finalBill.text = String.format("\nSplit Bill: No" +
                        "\nTip 1: %.2f $tipAmount" +
                        "\nTip 2: N/A" +
                        "\nTotal: %.2f $finalAmount")
            } else if(inputString.isNotEmpty() && yesButton.isActivated){
                val splitBill = billValue * 0.5
                val tipValue = splitBill * tip
                var individualAmount = tipValue + splitBill
                var finalAmount = individualAmount * 2
                finalBill.text = String.format("\nSplit Bill: Yes" +
                        "\nTip 1: %.2f $tipValue" +
                        "\nTip 2: %.2f $tipValue" +
                        "\nTotal: %.2f $finalAmount")
            }else {
                // Optional: Tell the user they forgot to enter a number
                Toast.makeText(this, "Please enter a value", Toast.LENGTH_SHORT).show()
            }//else end
        }
    }
}

