package com.example.imccalculator.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.imccalculator.data.IMCDataClass
import java.lang.NumberFormatException
import java.util.*
import javax.inject.Inject

class MainViewModel @Inject constructor () : ViewModel() {
    val error = MutableLiveData<Unit>()
    val calculationResult = MutableLiveData<IMCDataClass>()
    val emptyReturn = MutableLiveData<Unit>()

    fun trimInputsforData(input: String) {
        if (input.length == 1 && input.startsWith("0")) {
            input.replaceFirst("^0+(?!$)", "")
            emptyReturn.value = Unit
        }
    }

    fun validationNumberToDouble(number: String): Boolean {
        return try {
            number.toDouble()
            true
        } catch (e: NumberFormatException) {
            false
        }
    }

    fun finalValidationToCalculate(height: String, weight: String): Boolean {
        if (!validationNumberToDouble(height) || !validationNumberToDouble(weight)) {
            return false
        }
        return true
    }

    fun calculateBMI(height: String, weight: String) {
        if (finalValidationToCalculate(height, weight)) {
            val bmi = weight.toFloat() / (height.toFloat() * height.toFloat())
            val locale = Locale("EN", "US")
            val roundNumber = String.format(locale,"%.2f", bmi).toFloat()
            bmiCategorySelect(roundNumber)
        } else {
            error.value = Unit
        }
    }

    fun bmiCategorySelect(bmi: Float) {
        val bmiSetResult = IMCDataClass()
        bmiSetResult.bmi = bmi.toString()
        when {
            bmi < 18.5 -> {
                bmiSetResult.categoria = "Baixo Peso"
            }
            bmi in 18.5..24.9 -> {
                bmiSetResult.categoria = "Peso Normal"
            }
            bmi in 25.0..29.9 -> {
                bmiSetResult.categoria = "Excesso de Peso"
            }
            bmi in 30.0..34.9 -> {
                bmiSetResult.categoria = "Obesidade Moderada"
            }
            bmi in 35.0..39.9 -> {
                bmiSetResult.categoria = "Obesidade Severa"
            }
            bmi > 40.0 -> {
                bmiSetResult.categoria = "Obesidade Mórbida"
            }
        }
        calculationResult.value = bmiSetResult
    }
}