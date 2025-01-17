package com.example.newcalculetor.calculator

import kotlin.math.pow
import kotlin.math.sqrt

fun calculate(x: String): Float {
    val numbers = mutableListOf<Float>()
    val operators = mutableListOf<Char>()
    var num = ""
    var previousChar: Char? = null

    // Separar números y operadores
    for (i in x) {
        if (i.isDigit() ||
            (i == '-' && (previousChar == null || !previousChar.isDigit())) ||
            (i == '.' && num.isNotEmpty() && num.last().isDigit())
        ) { // Considerar números decimales y negativos
            num += i
        } else {
            if (num.isNotEmpty()) {
                numbers.add(num.toFloat())
                num = ""
            }
            if (i in "+-*/^√()") {
                operators.add(i)
            }
        }
        previousChar = i
    }
    if (num.isNotEmpty()) {
        numbers.add(num.toFloat())
    }

    var index = 0
    while (index < operators.size) {
        when (operators[index]) {
            '√' -> {
                if (numbers.size > operators.size) {  //Correct position
                    val result = sqrt(numbers[index + 1])
                    numbers[index + 1] = result
                    operators.removeAt(index) // remover la operacion
                    operators.add(index, '*')

                } else {
                    val result = sqrt(numbers[index])
                    numbers[index] = result
                    operators.removeAt(index)
                }
            }

            '^' -> {
                if (index + 1 < numbers.size) {
                    val result = numbers[index].pow(numbers[index + 1])
                    numbers[index] = result
                    numbers.removeAt(index + 1)
                    operators.removeAt(index) // Eliminar el operador
                } else {
                    throw IndexOutOfBoundsException("No hay suficientes números para la operación de potencia.")
                }
            }

            '*' -> {
                if (index + 1 < numbers.size) {
                    val result = numbers[index] * numbers[index + 1]
                    numbers[index] = result
                    numbers.removeAt(index + 1)
                    operators.removeAt(index)
                } else {
                    throw IndexOutOfBoundsException("No hay suficientes números para la multiplicación.")
                }
            }

            '/' -> {
                if (index + 1 < numbers.size) {
                    try {
                        val result = numbers[index] / numbers[index + 1]
                        numbers[index] = result
                        numbers.removeAt(index + 1)
                        operators.removeAt(index)
                    } catch (e: ArithmeticException) {
                        e.printStackTrace()
                        println(e.message)
                    }
                } else {
                    throw IndexOutOfBoundsException("No hay suficientes números para la división.")
                }
            }

            '+' -> {
                if (index + 1 < numbers.size) {
                    val result = numbers[index] + numbers[index + 1]
                    numbers[index] = result
                    numbers.removeAt(index + 1)
                    operators.removeAt(index)
                } else {
                    throw IndexOutOfBoundsException("No hay suficientes números para la suma.")
                }
            }

            '-' -> {
                if (index + 1 < numbers.size) {
                    val result = numbers[index] - numbers[index + 1]
                    numbers[index] = result
                    numbers.removeAt(index + 1)
                    operators.removeAt(index)
                } else {
                    throw IndexOutOfBoundsException("No hay suficientes números para la resta.")
                }
            }

            else -> index++ // Para operadores no reconocidos
        }
    }

    // Solo debería quedar un número al final
    return numbers.firstOrNull() ?: throw IllegalStateException("Error en la expresión.")
}