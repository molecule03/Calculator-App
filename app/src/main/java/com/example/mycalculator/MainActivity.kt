package com.example.mycalculator

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    // Boolean variables to keep track of the last input
    var lastNum: Boolean = false
    var lastDot: Boolean = false

    /**
     * Appends the clicked digit to the output TextView and sets lastNum to true.
     */
    fun OnDigit(view: View) {
        val tvOutput: TextView = findViewById(R.id.tvOutput)
        tvOutput.append((view as Button).text)
        lastNum = true
    }

    /**
     * Clears the output TextView and resets lastNum and lastDot to false.
     */
    fun OnClear(view: View) {
        val tvOutput: TextView = findViewById(R.id.tvOutput)
        tvOutput.text = ""
        lastNum = false
        lastDot = false
    }

    /**
     * Appends the decimal point to the output TextView if lastNum is true and lastDot is false.
     * Then, it sets lastNum to false and lastDot to true.
     */
    fun OnDecimal(view: View) {
        if (lastNum && !lastDot) {
            val tvOutput: TextView = findViewById(R.id.tvOutput)
            tvOutput.append((view as Button).text)
            lastNum = false
            lastDot = true
        }
    }

    /**
     * Appends the clicked operator to the output TextView if lastNum is true and an operator is not already added.
     * Then, it sets lastNum and lastDot to false.
     */
    fun OnOperator(view: View) {
        val tvOutput: TextView = findViewById(R.id.tvOutput)
        if (lastNum && !isOperatorAdded(tvOutput.text.toString())) {
            tvOutput.append((view as Button).text)
            lastNum = false
            lastDot = false
        }
    }

    /**
     * Performs the arithmetic calculation based on the expression in the output TextView.
     * It handles addition, subtraction, multiplication, and division.
     * The result is displayed in the output TextView.
     */
    fun OnEquals(view: View) {
        val tvOutput: TextView = findViewById(R.id.tvOutput)

        // Check if the last character is a number to avoid errors
        if (lastNum) {
            var tvValue = tvOutput.text.toString()
            var prefix = ""
            try {
                // Checking if the expression starts with a minus sign
                if (tvValue[0] == '-') {
                    prefix = "-"
                    tvValue = tvValue.substring(1)
                }

                // Calculation for subtraction
                if (tvValue.contains("-")) {
                    val splitValue = tvValue.split("-")
                    var fir = splitValue[0].toDouble()
                    var sec = splitValue[1].toDouble()

                    if (prefix == "-") fir *= -1
                    val ans = (fir - sec)
                    tvOutput.text = removeZeros(ans)
                }
                // Calculation for addition
                else if (tvValue.contains("+")) {
                    val splitValue = tvValue.split("+")
                    var fir = splitValue[0].toDouble()
                    var sec = splitValue[1].toDouble()

                    if (prefix == "-") fir *= -1
                    val ans = (fir + sec)
                    tvOutput.text = removeZeros(ans)
                }
                // Calculation for multiplication
                else if (tvValue.contains("*")) {
                    val splitValue = tvValue.split("*")
                    var fir = splitValue[0].toDouble()
                    var sec = splitValue[1].toDouble()

                    if (prefix == "-") fir *= -1
                    val ans = (fir * sec)
                    tvOutput.text = removeZeros(ans)
                }
                // Calculation for division
                else if (tvValue.contains("/")) {
                    val splitValue = tvValue.split("/")
                    var fir = splitValue[0].toDouble()
                    var sec = splitValue[1].toDouble()

                    if (prefix == "-") fir *= -1
                    val ans = (fir / sec)
                    tvOutput.text = removeZeros(ans)
                }
            } catch (e: ArithmeticException) {
                e.printStackTrace()
            }
        }
    }

    /**
     * Removes redundant zeros from the result by converting it to an integer if it is a whole number.
     */
    private fun removeZeros(ans: Double): String {
        val intAns = ans.toInt()
        return if (intAns.toDouble() == ans) intAns.toString() else ans.toString()
    }

    /**
     * Checks if an operator is already added to the expression.
     */
    private fun isOperatorAdded(string: String): Boolean {
        if (string.startsWith("-")) return false
        return (string.contains("-") || string.contains("+") ||
                string.contains("*") || string.contains("/"))
    }
}
