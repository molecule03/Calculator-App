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

    var lastNum : Boolean = false;
    var lastDot : Boolean = false;

    fun OnDigit(view : View){

        val tvOutput : TextView = findViewById(R.id.tvOutput)
        tvOutput.append((view as Button).text)

//        If click on num lastNum will be become true
        lastNum = true;
    }

    fun OnClear(view : View){

        val tvOutput : TextView = findViewById(R.id.tvOutput)
        tvOutput.text = "";

//        Screen Cleared so lastNum & lastDot will become false
        lastNum = false
        lastDot = false
    }

    fun OnDecimal(view : View){

        if(lastNum && !lastDot){
            val tvOutput : TextView = findViewById(R.id.tvOutput)
            tvOutput.append((view as Button).text)

//            Click and do to lastNum will become false and lastDot will become true;
            lastNum = false;
            lastDot = true;
        }
    }

    fun OnOperator(view : View){

        val tvOutput : TextView = findViewById(R.id.tvOutput);
        if(lastNum && !isOperatorAdded(tvOutput.text.toString())) {
            tvOutput.append((view as Button).text)

//            Click on operator so both will become false
            lastNum = false;
            lastDot = false;
        }
    }


    fun OnEquals(view : View){

        val tvOutput : TextView = findViewById(R.id.tvOutput);

//        Important to check is the last char is Number(For eg: 619+ -> this will give error)
            if (lastNum) {
                var tvValue = tvOutput.text.toString()
                var prefix = "";
                try {
//                    Checking if the expression is starting with minus
                    if (tvValue[0] == '-'){
                        prefix="-"
                        tvValue = tvValue.substring(1);
                    }
//                    Calculation for subtraction
                    if (tvValue.contains("-")) {
                        var splitValue = tvValue.split("-")

                        var fir = splitValue[0].toDouble();
                        var sec = splitValue[1].toDouble();

                        if(prefix == "-") fir *= -1;
                        var ans = (fir - sec);
                        tvOutput.text = removeZeros(ans);
                    }
//                    Calculation for Addition
                    else if (tvValue.contains("+")) {
                        var splitValue = tvValue.split("+")

                        var fir = splitValue[0].toDouble();
                        var sec = splitValue[1].toDouble();

                        if(prefix == "-") fir *= -1;
                        var ans = (fir + sec);
                        tvOutput.text = removeZeros(ans);
                    }
//                    Calculation for Multiplication
                    else if (tvValue.contains("*")) {
                        var splitValue = tvValue.split("*")

                        var fir = splitValue[0].toDouble();
                        var sec = splitValue[1].toDouble();

                        if(prefix == "-") fir *= -1;
                        var ans = (fir * sec);
                        tvOutput.text = removeZeros(ans);
                    }
//                    Calculation for Division
                    else if (tvValue.contains("/")) {
                        var splitValue = tvValue.split("/")

                        var fir = splitValue[0].toDouble();
                        var sec = splitValue[1].toDouble();

                        if(prefix == "-") fir *= -1;
                        var ans = (fir / sec);
                        tvOutput.text = removeZeros(ans);
                    }

                }
                catch(e : ArithmeticException){
                    e.printStackTrace()
                };
            }

    }

//    Creating fun to remove reduntact zeros (For eg: 5.00 -> 5)
    private fun removeZeros(ans : Double) : String{
        var intAns = ans.toInt();
        return if (intAns.toDouble() == ans) intAns.toString() else ans.toString()
    }

//    Checking if the expression has any oprators or not (For eg: 10/2)
    private fun isOperatorAdded(string : String) : Boolean{
        if(string.startsWith("-")) return false;

        return ( string.contains("-") || string.contains("+") ||
            string.contains("*") || string.contains("/") )
    }


}