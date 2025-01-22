package com.example.newcalculetor.calculator

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class CalculateViewModel: ViewModel() {

    var equacionParaUI by mutableStateOf("")
    private var equacionParaCalcular by mutableStateOf("")
    var result by mutableFloatStateOf(0.0f)

    fun appendToEquation(value: String) {
        equacionParaUI += when (value) {
            "÷" -> " ÷ "
            "×" -> " × "
            "-" -> " - "
            "+" -> " + "
            "^" -> " ^ "
            else -> value
        }
    }

    fun appendToEquationCalculate(value: String) {
        equacionParaCalcular += when (value) {
            "×" -> "*"
            "÷" -> "/"
            "ans" -> "${this.result}"
            else -> value
        }
    }

    fun deleteAll(){
        equacionParaUI = ""
        equacionParaCalcular = ""
        result = 0.0f
    }

    fun deleteLast() {
        if (equacionParaUI.isEmpty()) return // Si la cadena está vacía, no hacemos nada.

        when {
            equacionParaUI == "Error" -> {
                // Reiniciar la ecuación y el resultado si hay un error.
                equacionParaUI = ""
                equacionParaCalcular = ""
                result = 0.0f
            }

            equacionParaUI.length >= 3 &&
                    equacionParaUI[equacionParaUI.length - 2] in listOf('+', '-', '÷', '×', '√', '^')
            -> {
                // Eliminar los últimos tres caracteres si es un operador con espacios
                equacionParaUI = equacionParaUI.dropLast(3)
                equacionParaCalcular = equacionParaCalcular.dropLast(3)
            }

            equacionParaUI.endsWith("ans")//[equacionParaUI.length - 2] == 'n' -> {
                    -> {
                // Manejo especial para "ans".
                equacionParaUI = equacionParaUI.dropLast(3)
                equacionParaCalcular = equacionParaCalcular.replace("$result","")
            }

            else -> {
                // Caso general: eliminar el último carácter.
                equacionParaUI = equacionParaUI.dropLast(1)
                equacionParaCalcular = equacionParaCalcular.dropLast(1)
            }
        }
    }

    fun calculate(){
        try {
            result = Calculator().calculate(equacionParaCalcular)
            equacionParaCalcular = ""
            equacionParaUI = ""
        } catch (e: Exception) {
            equacionParaUI = "Error"
            equacionParaCalcular = ""
            result = 0.0f
        }
    }

}