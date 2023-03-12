package com.example.calculator_converter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.widget.EditText
import com.example.calculator_converter.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var viewBinding :ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
       viewBinding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(viewBinding.root)

        viewBinding.resetBtn.setOnClickListener {
                viewBinding.hexaDecimal.setText("")
                viewBinding.octal.setText("")
                viewBinding.binary.setText("")
                viewBinding.decimal.setText("")
        }

       viewBinding.result.setOnClickListener {

        val decimalResultFromBinary=  binaryToDecimal(viewBinding.binary?.text.toString() )
        val octalResultFromBinary=  binaryToOctal(viewBinding.binary?.text.toString() )
        val hexaResultFromBinary=  binaryToHex(viewBinding.binary?.text.toString() )

           viewBinding.octal.setText(octalResultFromBinary)
           viewBinding.decimal.setText(decimalResultFromBinary)
           viewBinding.hexaDecimal.setText(hexaResultFromBinary)

    }
}
    }

    fun binaryToDecimal(binaNumber: String): String {
        var ans = 0L
        var multOfTow = 1L
        for (i in binaNumber.length - 1 downTo 0) {
            if (binaNumber[i] == '1') {
                ans += multOfTow
            }
            multOfTow *= 2
        }
        return "$ans"
    }
    fun binaryToOctal(binaryNumber: String): String {
        var st = binaryNumber.length
        var ans = ""
        while (st > 2) {
            val decimalFourNumber = binaryToDecimal(binaryNumber.substring(st - 3, st))
            ans = decimalFourNumber + ans
            st -= 3
        }
        if (st > 0) ans = binaryToDecimal(binaryNumber.substring(0, st)) + ans
        return ans
    }
   fun binaryToHex(binaryNumber: String): String {
    var st = binaryNumber.length
    var ans = ""
    while (st > 3) {
        val decimalFourNumber =
            decimalToHex(binaryToDecimal(binaryNumber.substring(st - 4, st)))
        ans = decimalFourNumber + ans
        st -= 4
    }
    if (st > 0) ans = decimalToHex(binaryToDecimal(binaryNumber.substring(0, st))) + ans
    return ans
}

    fun decimalToHex(decimalNumber: String): String {
        return if (decimalNumber == "10") "A"
        else if (decimalNumber == "11") "B"
        else if (decimalNumber == "12") "C"
        else if (decimalNumber == "13") "D"
        else if (decimalNumber == "14") "E"
        else if (decimalNumber == "15") "F"
        else decimalNumber
    }

