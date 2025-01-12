package com.example.newcalculetor.calculator

fun calculate(x: String): Double {
    val numbers = mutableListOf<Double>()
    val operators = mutableListOf<Char>()
    var num = ""

    // Separar números y operadores
    for (i in x) {
        if (i.isDigit() || i == '.') { // Considerar números decimales
            num += i
        } else if (i == '+' || i == '-' || i == '*' || i == '/') {
            if (num.isNotEmpty()) {
                numbers.add(num.toDouble())
                num = ""
            }
            operators.add(i)
        }
    }
    if (num.isNotEmpty()) {
        numbers.add(num.toDouble())
    }

    // Resolver multiplicaciones y divisiones
    var index = 0
    while (index < operators.size) {
        if (operators[index] == '*' || operators[index] == '/') {
            val result = if (operators[index] == '*') {
                numbers[index] * numbers[index + 1]
            } else {
                numbers[index] / numbers[index + 1]
            }
            numbers[index] = result
            numbers.removeAt(index + 1) // Eliminar el segundo número ya procesado
            operators.removeAt(index) // Eliminar el operador ya procesado
        } else {
            index++
        }
    }

    // Resolver sumas y restas
    var equation = numbers[0]
    for (i in operators.indices) {
        equation = if (operators[i] == '+') {
            equation + numbers[i + 1]
        } else {
            equation - numbers[i + 1]
        }
    }

    return equation
}