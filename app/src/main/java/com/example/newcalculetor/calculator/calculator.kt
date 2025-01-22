package com.example.newcalculetor.calculator

import kotlin.math.pow
import kotlin.math.sqrt

class Calculator() {
    private val listNumbers = mutableListOf<Float>()
    private val listOperators = mutableListOf<Char>()

    private fun conversionDeString(equation: String) {
        var num = ""
        var previousChar: Char? = null

        for (i in equation) {
            val isNumberNegative = i == '-' && (previousChar == null || !previousChar.isDigit())
            val isNumberFloat = i == '.' && num.isNotEmpty() && num.last().isDigit()

            if (i.isDigit()||isNumberNegative||isNumberFloat) {
                num += i
            } else {
                if (num.isNotEmpty()) {
                    listNumbers.add(num.toFloat())
                    num = ""
                }
                if (i in "+-*/^√") {
                    listOperators.add(i)
                }else{
                    throw IndexOutOfBoundsException("el operador no es valido")
                }
            }
            previousChar = i
        }
        if (num.isNotEmpty()) listNumbers.add( num.toFloat() )
    }

    fun calculate(equation: String) : Float {
        conversionDeString(equation)
        var index = 0

        while (index < listOperators.size) {

            when (listOperators[index]) {
                '√' -> {
                    if (listNumbers.size > listOperators.size) {
                        val result = sqrt(listNumbers[index + 1])

                        listNumbers[index + 1] = result
                        listOperators.removeAt(index) // remover la operacion
                        listOperators.add(index, '*')
                    } else {
                        val result = sqrt(listNumbers[index])

                        listNumbers[index] = result
                        listOperators.removeAt(index)
                    }
                }

                '^' -> {
                    if (index + 1 < listNumbers.size) {
                        val result = listNumbers[index].pow(listNumbers[index + 1])

                        listNumbers[index] = result
                        listNumbers.removeAt(index + 1)
                        listOperators.removeAt(index) // Eliminar el operador
                    } else {
                        throw IndexOutOfBoundsException("No hay suficientes números para la operación de potencia.")
                    }
                }

                '*' -> {
                    if (index + 1 < listNumbers.size) {
                        val result = listNumbers[index] * listNumbers[index + 1]

                        listNumbers[index] = result
                        listNumbers.removeAt(index + 1)
                        listOperators.removeAt(index)
                    } else {
                        throw IndexOutOfBoundsException("No hay suficientes números para la multiplicación.")
                    }
                }

                '/' -> {
                    if (index + 1 < listNumbers.size) {
                        try {
                            val result = listNumbers[index] / listNumbers[index + 1]

                            listNumbers[index] = result
                            listNumbers.removeAt(index + 1)
                            listOperators.removeAt(index)
                        } catch (e: ArithmeticException) {
                            println(e.message)
                        }
                    } else {
                        throw IndexOutOfBoundsException("No hay suficientes números para la división.")
                    }
                }

                '+' -> {
                    if (index + 1 < listNumbers.size) {
                        val result = listNumbers[index] + listNumbers[index + 1]

                        listNumbers[index] = result
                        listNumbers.removeAt(index + 1)
                        listOperators.removeAt(index)
                    } else {
                        throw IndexOutOfBoundsException("No hay suficientes números para la suma.")
                    }
                }

                '-' -> {
                    if (index + 1 < listNumbers.size) {
                        val result = listNumbers[index] - listNumbers[index + 1]

                        listNumbers[index] = result
                        listNumbers.removeAt(index + 1)
                        listOperators.removeAt(index)
                    } else {
                        throw IndexOutOfBoundsException("No hay suficientes números para la resta.")
                    }
                }

                else -> index++ // Para operadores no reconocidos
            }
        }
        return listNumbers.firstOrNull() ?: throw IllegalStateException("Error en la expresión.")
    }
}