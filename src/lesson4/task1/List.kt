@file:Suppress("UNUSED_PARAMETER", "ConvertCallChainIntoSequence")

package lesson4.task1

import lesson1.task1.discriminant
import lesson1.task1.sqr
import lesson3.task1.digitNumber
import lesson3.task1.pow
import kotlin.math.sqrt

// Урок 4: списки
// Максимальное количество баллов = 12
// Рекомендуемое количество баллов = 8
// Вместе с предыдущими уроками = 24/33

fun isPrime(n: Int) = n >= 2 && (2..n / 2).all { n % it != 0 }

/**
 * Пример
 *
 * Найти все корни уравнения x^2 = y
 */
fun sqRoots(y: Double) =
    when {
        y < 0 -> listOf()
        y == 0.0 -> listOf(0.0)
        else -> {
            val root = sqrt(y)
            // Результат!
            listOf(-root, root)
        }
    }

/**
 * Пример
 *
 * Найти все корни биквадратного уравнения ax^4 + bx^2 + c = 0.
 * Вернуть список корней (пустой, если корней нет)
 */
fun biRoots(a: Double, b: Double, c: Double): List<Double> {
    if (a == 0.0) {
        return if (b == 0.0) listOf()
        else sqRoots(-c / b)
    }
    val d = discriminant(a, b, c)
    if (d < 0.0) return listOf()
    if (d == 0.0) return sqRoots(-b / (2 * a))
    val y1 = (-b + sqrt(d)) / (2 * a)
    val y2 = (-b - sqrt(d)) / (2 * a)
    return sqRoots(y1) + sqRoots(y2)
}

/**
 * Пример
 *
 * Выделить в список отрицательные элементы из заданного списка
 */
fun negativeList(list: List<Int>): List<Int> {
    val result = mutableListOf<Int>()
    for (element in list) {
        if (element < 0) {
            result.add(element)
        }
    }
    return result
}

/**
 * Пример
 *
 * Изменить знак для всех положительных элементов списка
 */
fun invertPositives(list: MutableList<Int>) {
    for (i in 0 until list.size) {
        val element = list[i]
        if (element > 0) {
            list[i] = -element
        }
    }
}

/**
 * Пример
 *
 * Из имеющегося списка целых чисел, сформировать список их квадратов
 */
fun squares(list: List<Int>) = list.map { it * it }

/**
 * Пример
 *
 * Из имеющихся целых чисел, заданного через vararg-параметр, сформировать массив их квадратов
 */
fun squares(vararg array: Int) = squares(array.toList()).toTypedArray()

/**
 * Пример
 *
 * По заданной строке str определить, является ли она палиндромом.
 * В палиндроме первый символ должен быть равен последнему, второй предпоследнему и т.д.
 * Одни и те же буквы в разном регистре следует считать равными с точки зрения данной задачи.
 * Пробелы не следует принимать во внимание при сравнении символов, например, строка
 * "А роза упала на лапу Азора" является палиндромом.
 */
fun isPalindrome(str: String): Boolean {
    val lowerCase = str.toLowerCase().filter { it != ' ' }
    for (i in 0..lowerCase.length / 2) {
        if (lowerCase[i] != lowerCase[lowerCase.length - i - 1]) return false
    }
    return true
}

/**
 * Пример
 *
 * По имеющемуся списку целых чисел, например [3, 6, 5, 4, 9], построить строку с примером их суммирования:
 * 3 + 6 + 5 + 4 + 9 = 27 в данном случае.
 */
fun buildSumExample(list: List<Int>) = list.joinToString(separator = " + ", postfix = " = ${list.sum()}")

/**
 * Простая (2 балла)
 *
 * Найти модуль заданного вектора, представленного в виде списка v,
 * по формуле abs = sqrt(a1^2 + a2^2 + ... + aN^2).
 * Модуль пустого вектора считать равным 0.0.
 */
fun abs(v: List<Double>): Double {
    var abs = 0.0
    for (element in v) {
        abs += sqr(element)
    }
    return sqrt(abs)
}

/**
 * Простая (2 балла)
 *
 * Рассчитать среднее арифметическое элементов списка list. Вернуть 0.0, если список пуст
 */
fun mean(list: List<Double>): Double {
    if (list.isEmpty()) return 0.0
    return list.sum() / list.size
}

/**
 * Средняя (3 балла)
 *
 * Центрировать заданный список list, уменьшив каждый элемент на среднее арифметическое всех элементов.
 * Если список пуст, не делать ничего. Вернуть изменённый список.
 *
 * Обратите внимание, что данная функция должна изменять содержание списка list, а не его копии.
 */
fun center(list: MutableList<Double>): MutableList<Double> {
    if (list.isEmpty()) return list
    val mean = list.sum() / list.size
    for (i in 0 until list.size) list[i] -= mean
    return list
}

/**
 * Средняя (3 балла)
 *
 * Найти скалярное произведение двух векторов равной размерности,
 * представленные в виде списков a и b. Скалярное произведение считать по формуле:
 * C = a1b1 + a2b2 + ... + aNbN. Произведение пустых векторов считать равным 0.
 */
fun times(a: List<Int>, b: List<Int>): Int {
    var c = 0
    for (i in a.indices) c += (a[i] * b[i])
    return c
}

/**
 * Средняя (3 балла)
 *
 * Рассчитать значение многочлена при заданном x:
 * p(x) = p0 + p1*x + p2*x^2 + p3*x^3 + ... + pN*x^N.
 * Коэффициенты многочлена заданы списком p: (p0, p1, p2, p3, ..., pN).
 * Значение пустого многочлена равно 0 при любом x.
 */
fun polynom(p: List<Int>, x: Int): Int {
    if (p.isEmpty()) return 0
    var result = p[0]
    for (i in 1 until p.size) result += p[i] * pow(x, i)
    return result
}

/**
 * Средняя (3 балла)
 *
 * В заданном списке list каждый элемент, кроме первого, заменить
 * суммой данного элемента и всех предыдущих.
 * Например: 1, 2, 3, 4 -> 1, 3, 6, 10.
 * Пустой список не следует изменять. Вернуть изменённый список.
 *
 * Обратите внимание, что данная функция должна изменять содержание списка list, а не его копии.
 */
fun accumulate(list: MutableList<Int>): MutableList<Int> {
    if (list.isEmpty()) return list
    var sum = list[0]
    for (i in 1 until list.size) {
        sum += list[i]
        list[i] = sum
    }
    return list
}

/**
 * Средняя (3 балла)
 *
 * Разложить заданное натуральное число n > 1 на простые множители.
 * Результат разложения вернуть в виде списка множителей, например 75 -> (3, 5, 5).
 * Множители в списке должны располагаться по возрастанию.
 */
fun factorize(n: Int): List<Int> {
    val list = mutableListOf<Int>()
    if (isPrime(n)) return listOf(n)
    var nCpy = n
    var i = 2
    var sum = 1
    while (nCpy != 1) {
        if (nCpy % i == 0) {
            list.add(i)
            nCpy /= i
            sum *= i
        } else if (nCpy % i != 0) i++
    }
    return list
}

/**
 * Сложная (4 балла)
 *
 * Разложить заданное натуральное число n > 1 на простые множители.
 * Результат разложения вернуть в виде строки, например 75 -> 3*5*5
 * Множители в результирующей строке должны располагаться по возрастанию.
 */
fun factorizeToString(n: Int): String = factorize(n).joinToString(separator = "*")

/**
 * Средняя (3 балла)
 *
 * Перевести заданное целое число n >= 0 в систему счисления с основанием base > 1.
 * Результат перевода вернуть в виде списка цифр в base-ичной системе от старшей к младшей,
 * например: n = 100, base = 4 -> (1, 2, 1, 0) или n = 250, base = 14 -> (1, 3, 12)
 */
fun convert(n: Int, base: Int): List<Int> {
    val list = mutableListOf<Int>()
    if (n <= base) {
        list.add(n)
        return list
    }
    var nCpy = n
    while (nCpy > 0) {
        list.add(0, nCpy % base)
        nCpy /= base
    }
    return list
}

/**
 * Сложная (4 балла)
 *
 * Перевести заданное целое число n >= 0 в систему счисления с основанием 1 < base < 37.
 * Результат перевода вернуть в виде строки, цифры более 9 представлять латинскими
 * строчными буквами: 10 -> a, 11 -> b, 12 -> c и так далее.
 * Например: n = 100, base = 4 -> 1210, n = 250, base = 14 -> 13c
 *
 * Использовать функции стандартной библиотеки, напрямую и полностью решающие данную задачу
 * (например, n.toString(base) и подобные), запрещается.
 */
fun convertToString(n: Int, base: Int): String {
    val convertN = convert(n, base)
    var str = ""
    for (i in convertN.indices)
        if (convertN[i] >= 10) str += ('a'.toInt() - 10 + convertN[i] % base).toChar()
        else str += convertN[i]
    return str
}

/**
 * Средняя (3 балла)
 *
 * Перевести число, представленное списком цифр digits от старшей к младшей,
 * из системы счисления с основанием base в десятичную.
 * Например: digits = (1, 3, 12), base = 14 -> 250
 */
fun decimal(digits: List<Int>, base: Int): Int {
    var result = 0
    for ((k, i) in (digits.size - 1 downTo 0).withIndex())
        result += digits[i] * pow(base, k)
    return result
}

/**
 * Сложная (4 балла)
 *
 * Перевести число, представленное цифровой строкой str,
 * из системы счисления с основанием base в десятичную.
 * Цифры более 9 представляются латинскими строчными буквами:
 * 10 -> a, 11 -> b, 12 -> c и так далее.
 * Например: str = "13c", base = 14 -> 250
 *
 * Использовать функции стандартной библиотеки, напрямую и полностью решающие данную задачу
 * (например, str.toInt(base)), запрещается.
 */
fun decimalFromString(str: String, base: Int): Int {
    var result = 0
    for ((k, i) in (str.length - 1 downTo 0).withIndex()) {
        result += if (str[i].isDigit()) (str[i].toInt() - '0'.toInt()) * pow(base, k)
        else (str[i].toInt() - 'a'.toInt() + 10) * pow(base, k)
    }
    return result
}

/**
 * Сложная (5 баллов)
 *
 * Перевести натуральное число n > 0 в римскую систему.
 * Римские цифры: 1 = I, 4 = IV, 5 = V, 9 = IX, 10 = X, 40 = XL, 50 = L,
 * 90 = XC, 100 = C, 400 = CD, 500 = D, 900 = CM, 1000 = M.
 * Например: 23 = XXIII, 44 = XLIV, 100 = C
 */

fun digitConverter(n: Int, step: Int): String {
    return when {
        n == 1 && step == 1 -> "I"
        n == 2 && step == 1 -> "II"
        n == 3 && step == 1 -> "III"
        n == 4 && step == 1 -> "IV"
        n == 5 && step == 1 -> "V"
        n == 6 && step == 1 -> "VI"
        n == 7 && step == 1 -> "VII"
        n == 8 && step == 1 -> "VIII"
        n == 9 && step == 1 -> "IX"

        n == 1 && step == 2 -> "X"
        n == 2 && step == 2 -> "XX"
        n == 3 && step == 2 -> "XXX"
        n == 4 && step == 2 -> "XL"
        n == 5 && step == 2 -> "L"
        n == 6 && step == 2 -> "LX"
        n == 7 && step == 2 -> "LXX"
        n == 8 && step == 2 -> "LXXX"
        n == 9 && step == 2 -> "XC"

        n == 1 && step == 3 -> "C"
        n == 2 && step == 3 -> "CC"
        n == 3 && step == 3 -> "CCC"
        n == 4 && step == 3 -> "CD"
        n == 5 && step == 3 -> "D"
        n == 6 && step == 3 -> "DC"
        n == 7 && step == 3 -> "DCC"
        n == 8 && step == 3 -> "DCCC"
        n == 9 && step == 3 -> "CM"

        n == 1 && step == 4 -> "M"
        n == 2 && step == 4 -> "MM"
        n == 3 && step == 4 -> "MMM"
        else -> ""
    }
}

fun roman(n: Int): String {
    var number = n
    val result = StringBuilder()
    for (step in digitNumber(n) downTo 1) {
        if (number / pow(10, step - 1) != 0)
            result.append(digitConverter(number / pow(10, step - 1), step))
        number %= pow(10, step - 1)
    }
    return result.toString()
}

/**
 * Очень сложная (7 баллов)
 *
 * Записать заданное натуральное число 1..999999 прописью по-русски.
 * Например, 375 = "триста семьдесят пять",
 * 23964 = "двадцать три тысячи девятьсот шестьдесят четыре"
 */

fun digitInString(n: Int, step: Int): String {
    return when {
        n == 1 && step == 1 -> "один "
        n == 2 && step == 1 -> "два "
        n == 3 && step == 1 -> "три "
        n == 4 && step == 1 -> "четыре "
        n == 5 && step == 1 -> "пять "
        n == 6 && step == 1 -> "шесть "
        n == 7 && step == 1 -> "семь "
        n == 8 && step == 1 -> "восемь "
        n == 9 && step == 1 -> "девять "

        n == 10 && step == 2 -> "десять "
        n == 11 && step == 2 -> "одиннадцать "
        n == 12 && step == 2 -> "двенадцать "
        n == 13 && step == 2 -> "тринадцать "
        n == 14 && step == 2 -> "четырнадцать "
        n == 15 && step == 2 -> "пятнадцать "
        n == 16 && step == 2 -> "шестнадцать "
        n == 17 && step == 2 -> "семнадцать "
        n == 18 && step == 2 -> "восемнадцать "
        n == 19 && step == 2 -> "девятнадцать "

        n == 2 && (step == 2 || step == 5) -> "двадцать "
        n == 3 && (step == 2 || step == 5) -> "тридцать "
        n == 4 && (step == 2 || step == 5) -> "сорок "
        n == 5 && (step == 2 || step == 5) -> "пятьдесят "
        n == 6 && (step == 2 || step == 5) -> "шестьдесят "
        n == 7 && (step == 2 || step == 5) -> "семьдесят "
        n == 8 && (step == 2 || step == 5) -> "восемьдесят "
        n == 9 && (step == 2 || step == 5) -> "девяносто "

        n == 1 && step == 3 -> "сто "
        n == 2 && step == 3 -> "двести "
        n == 3 && step == 3 -> "триста "
        n == 4 && step == 3 -> "четыреста "
        n == 5 && step == 3 -> "пятьсот "
        n == 6 && step == 3 -> "шестьсот "
        n == 7 && step == 3 -> "семьсот "
        n == 8 && step == 3 -> "восемьсот "
        n == 9 && step == 3 -> "девятьсот "

        n == 1 && step == 4 -> "одна тысяча "
        n == 2 && step == 4 -> "две тысячи "
        n == 3 && step == 4 -> "три тысячи "
        n == 4 && step == 4 -> "четыре тысячи "
        n == 5 && step == 4 -> "пять тысяч "
        n == 6 && step == 4 -> "шесть тысяч "
        n == 7 && step == 4 -> "семь тысяч "
        n == 8 && step == 4 -> "восемь тысяч "
        n == 9 && step == 4 -> "девять тысяч "

        n == 10 && step == 5 -> "десять тысяч "
        n == 11 && step == 5 -> "одиннадцать тысяч "
        n == 12 && step == 5 -> "двенадцать тысяч "
        n == 13 && step == 5 -> "тринадцать тысяч "
        n == 14 && step == 5 -> "четырнадцать тысяч "
        n == 15 && step == 5 -> "пятнадцать тысяч "
        n == 16 && step == 5 -> "шестнадцать тясяч "
        n == 17 && step == 5 -> "семнадцать тысяч "
        n == 18 && step == 5 -> "восемнадцать тысяч "
        n == 19 && step == 5 -> "девятнадцать тысяч "

        n == 1 && step == 6 -> "сто "
        n == 2 && step == 6 -> "двести "
        n == 3 && step == 6 -> "триста "
        n == 4 && step == 6 -> "четыреста "
        n == 5 && step == 6 -> "пятьсот "
        n == 6 && step == 6 -> "шестьсот "
        n == 7 && step == 6 -> "семьсот "
        n == 8 && step == 6 -> "восемьсот "
        n == 9 && step == 6 -> "девятьсот "
        else -> ""
    }
}

fun russian(n: Int): String {
    val result = StringBuilder()
    var number = n
    var step = digitNumber(n)
    while (step > 0) {
        if (step == 4 && digitNumber(number) < 4) {
            result.append("тысяч ")
            step = digitNumber(number)
        } else if ((step == 2 || step == 5) && number / pow(10, step - 1) == 1) {
            result.append(digitInString(10 + number % pow(10, step - 1) / pow(10, step - 2), step))
            number %= pow(10, step - 2)
            step -= 2
        } else if (number / pow(10, step - 1) != 0) {
            result.append(digitInString(number / pow(10, step - 1), step))
            number %= pow(10, step - 1)
            step--
        } else {
            number %= pow(10, step - 1)
            step--
        }
    }
    return result.toString().substring(0, result.length - 1)
}