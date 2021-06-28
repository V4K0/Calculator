package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.lang.ArithmeticException

var lastNumeric=false
var lastDot=false

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    lateinit var output: TextView
    fun onDigit(view: View) {
        output = findViewById<TextView>(R.id.tvInput)
        output.append((view as Button).text)
        lastNumeric = true
    }

    fun onClear(view: View) {
        output.text = ""
        lastNumeric = false
        lastDot = false
    }

    fun onDecimalPoint(view: View) {
        if (lastNumeric && !lastDot) {
            output.append(".")
            lastNumeric = false
            lastDot = true
        }
    }

    fun onOperator(view: View) {
        if (lastNumeric && !IsOperatorAdded(output.text.toString())) {
            output.append((view as Button).text)

            lastNumeric = false
            lastDot = false

        }
    }

    private fun IsOperatorAdded(value: String): Boolean {
        return if (value.startsWith("-")) {
            false
        } else {
            value.contains("/") || value.contains("X") || value.contains("+") || value.contains("-")
        }
    }

    private fun removeZeroAfterDot(result:String):String {
        var value = result
        if (value.contains(".0")) {
            value = result.substring(0, result.length - 2)

        }
        return value
    }

    fun onEqual(view: View) {
        if (lastNumeric) {
            var tvValue = output.text.toString()
            var prefix = ""
            try {

                if (tvValue.startsWith("-")) {
                    prefix = "-"
                    tvValue = tvValue.substring(1)
                }

                if (tvValue.contains("-")) {
                    val splitValue = tvValue.split("-")

                    var one = splitValue[0]
                    var two = splitValue[1]

                    if (!prefix.isEmpty()) {
                        one = prefix + one
                    }


                    output.text =removeZeroAfterDot( (one.toDouble() - two.toDouble()).toString())

                } else if (tvValue.contains("+")) {
                    val splitValue = tvValue.split("+")

                    var one = splitValue[0]
                    var two = splitValue[1]

                    if (!prefix.isEmpty()) {
                        one = prefix + one
                    }


                    output.text = removeZeroAfterDot((one.toDouble() + two.toDouble()).toString())


                } else if (tvValue.contains("X")) {
                    val splitValue = tvValue.split("X")

                    var one = splitValue[0]
                    var two = splitValue[1]

                    if (!prefix.isEmpty()) {
                        one = prefix + one
                    }


                    output.text =removeZeroAfterDot ((one.toDouble() * two.toDouble()).toString())

                } else if (tvValue.contains("/")) {
                    val splitValue = tvValue.split("/")

                    var one = splitValue[0]
                    var two = splitValue[1]

                    if (!prefix.isEmpty()) {
                        one = prefix + one
                    }


                    output.text = removeZeroAfterDot((one.toDouble() / two.toDouble()).toString())

                }

            }catch (e:ArithmeticException){

            }
        }
    }
}




