@file:Suppress("UNUSED_PARAMETER", "ConvertCallChainIntoSequence")

package lesson4.task1

import lesson1.task1.discriminant
import lesson3.task1.minDivisor
import java.lang.Math.pow
import kotlin.math.sqrt

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
 * Простая
 *
 * Найти модуль заданного вектора, представленного в виде списка v,
 * по формуле abs = sqrt(a1^2 + a2^2 + ... + aN^2).
 * Модуль пустого вектора считать равным 0.0.
 */
fun abs(v: List<Double>): Double = if (v.isNotEmpty()) Math.sqrt(v.map { it * it }.sum()) else 0.0

/**
 * Простая
 *
 * Рассчитать среднее арифметическое элементов списка list. Вернуть 0.0, если список пуст
 */
fun mean(list: List<Double>): Double = if (list.isNotEmpty()) list.sum() / list.count() else 0.0

/**
 * Средняя
 *
 * Центрировать заданный список list, уменьшив каждый элемент на среднее арифметическое всех элементов.
 * Если список пуст, не делать ничего. Вернуть изменённый список.
 *
 * Обратите внимание, что данная функция должна изменять содержание списка list, а не его копии.
 */
fun center(list: MutableList<Double>): MutableList<Double> {
    val mean = mean(list)
    if (mean == 0.0) return list
    return list.map { it - mean }.toMutableList()
}

/**
 * Средняя
 *
 * Найти скалярное произведение двух векторов равной размерности,
 * представленные в виде списков a и b. Скалярное произведение считать по формуле:
 * C = a1b1 + a2b2 + ... + aNbN. Произведение пустых векторов считать равным 0.0.
 */
fun times(a: List<Double>, b: List<Double>): Double {
    var sum = 0.0
    for (i in 0 until a.size)
        sum += a[i] * b[i]
    return sum
}

/**
 * Средняя
 *
 * Рассчитать значение многочлена при заданном x:
 * p(x) = p0 + p1*x + p2*x^2 + p3*x^3 + ... + pN*x^N.
 * Коэффициенты многочлена заданы списком p: (p0, p1, p2, p3, ..., pN).
 * Значение пустого многочлена равно 0.0 при любом x.
 */
fun polynom(p: List<Double>, x: Double): Double {
    if (p.isEmpty()) return 0.0
    var poly = 0.0
    for (i in 0 until p.size)
        poly += p[i] * Math.pow(x, i.toDouble())
    return poly
}

/**
 * Средняя
 *
 * В заданном списке list каждый элемент, кроме первого, заменить
 * суммой данного элемента и всех предыдущих.
 * Например: 1, 2, 3, 4 -> 1, 3, 6, 10.
 * Пустой список не следует изменять. Вернуть изменённый список.
 *
 * Обратите внимание, что данная функция должна изменять содержание списка list, а не его копии.
 */
fun accumulate(list: MutableList<Double>): MutableList<Double> {
    if (list.isEmpty()) return list
    var prev = list[0]
    for (i in 1 until list.size) {
        list[i] += prev
        prev = list[i]
    }
    return list
}

/**
 * Средняя
 *
 * Разложить заданное натуральное число n > 1 на простые множители.
 * Результат разложения вернуть в виде списка множителей, например 75 -> (3, 5, 5).
 * Множители в списке должны располагаться по возрастанию.
 */
fun factorize(n: Int): List<Int> {
    val list = mutableListOf<Int>()
    var t = n
    while (t > 1) {
        val minDiv = minDivisor(t)
        list.add(minDiv)
        t /= minDiv
    }
    return list
}

/**
 * Сложная
 *
 * Разложить заданное натуральное число n > 1 на простые множители.
 * Результат разложения вернуть в виде строки, например 75 -> 3*5*5
 * Множители в результирующей строке должны располагаться по возрастанию.
 */
fun factorizeToString(n: Int): String = factorize(n).sorted().joinToString(separator = "*")

/**
 * Средняя
 *
 * Перевести заданное целое число n >= 0 в систему счисления с основанием base > 1.
 * Результат перевода вернуть в виде списка цифр в base-ичной системе от старшей к младшей,
 * например: n = 100, base = 4 -> (1, 2, 1, 0) или n = 250, base = 14 -> (1, 3, 12)
 */
fun convert(n: Int, base: Int): List<Int> {
    val result = mutableListOf<Int>()
    var thisInt = n
    while (thisInt > 0) {
        val thisMod = thisInt % base
        thisInt /= base
        result.add(0, thisMod)
    }
    return result
}

/**
 * Сложная
 *
 * Перевести заданное целое число n >= 0 в систему счисления с основанием 1 < base < 37.
 * Результат перевода вернуть в виде строки, цифры более 9 представлять латинскими
 * строчными буквами: 10 -> a, 11 -> b, 12 -> c и так далее.
 * Например: n = 100, base = 4 -> 1210, n = 250, base = 14 -> 13c
 */
fun convertToString(n: Int, base: Int): String =
        convert(n, base)
                .map { if (it > 9) (55 + it).toChar().toString().toLowerCase() else it.toString() }
                .joinToString(separator = "")

/**
 * Средняя
 *
 * Перевести число, представленное списком цифр digits от старшей к младшей,
 * из системы счисления с основанием base в десятичную.
 * Например: digits = (1, 3, 12), base = 14 -> 250
 */
fun decimal(digits: List<Int>, base: Int): Int {
    var result = 0
    var it = 1
    for (d in digits) {
        result += d * pow(base.toDouble(), (digits.size - it).toDouble()).toInt()
        it++
    }
    return result
}

/**
 * Сложная
 *
 * Перевести число, представленное цифровой строкой str,
 * из системы счисления с основанием base в десятичную.
 * Цифры более 9 представляются латинскими строчными буквами:
 * 10 -> a, 11 -> b, 12 -> c и так далее.
 * Например: str = "13c", base = 14 -> 250
 */
fun decimalFromString(str: String, base: Int): Int {
    val dig = mutableListOf<Int>()
    for (c in str.toUpperCase())
        dig.add(if (c in 'A'..'Z') c.toInt() - 55 else c.toString().toInt())
    return decimal(dig, base)
}

/**
 * Сложная
 *
 * Перевести натуральное число n > 0 в римскую систему.
 * Римские цифры: 1 = I, 4 = IV, 5 = V, 9 = IX, 10 = X, 40 = XL, 50 = L,
 * 90 = XC, 100 = C, 400 = CD, 500 = D, 900 = CM, 1000 = M.
 * Например: 23 = XXIII, 44 = XLIV, 100 = C
 */
fun roman(n: Int): String {
    val a = arrayOf("I", "IV", "V", "IX", "X", "XL", "L", "XC", "C", "CD", "D", "CM", "M")
    val b = arrayOf(1, 4, 5, 9, 10, 40, 50, 90, 100, 400, 500, 900, 1000)
    var toRoman = n
    val result = mutableListOf<String>()
    for (i in a.size - 1 downTo 0) {
        while (toRoman >= b[i]) {
            result.add(a[i])
            toRoman -= b[i]
        }
    }
    return result.joinToString(separator = "")
}
/** Run test - 111 ms */
//fun roman(n: Int): String {
//    val result = mutableListOf<String>()
//    var tValue = n
//    var iteration = 0
//    while (iteration < digitNumber(n)) {
//        result.add(0, getRomanString(tValue % 10, iteration))
//        tValue /= 10
//        iteration++
//    }
//    return result.joinToString(separator = "")
//}

//val listRomanVal = arrayOf("I", "V", "X", "L", "C", "D", "M")
//fun getRomanString(deg: Int, i: Int) =
//        if (i >= 3) Array(deg) { listRomanVal[listRomanVal.size - 1] }.joinToString(separator = "")
//        else
//            when (deg) {
//                0 -> ""
//                1 -> listRomanVal[2 * i]
//                4 -> listRomanVal[2 * i] + listRomanVal[2 * i + 1]
//                5 -> listRomanVal[2 * i + 1]
//                9 -> listRomanVal[2 * i] + listRomanVal[2 * i + 2]
//                else -> {
//                    val strRom = Array(if (deg < 5) deg else deg - 5) { listRomanVal[2 * i] }.joinToString(separator = "")
//                    val pref = if (deg < 5) "" else listRomanVal[2 * i + 1]
//                    pref + strRom
//                }
//            }

/**
 * Очень сложная
 *
 * Записать заданное натуральное число 1..999999 прописью по-русски.
 * Например, 375 = "триста семьдесят пять",
 * 23964 = "двадцать три тысячи девятьсот шестьдесят четыре"
 */
fun russian(n: Int): String {
    var tNum = n
    var iteration = 0
    val result = mutableListOf<String>()
    while (tNum > 0) {
        result.add(0, getStringFromNumToTause(tNum % 1000, iteration))
        iteration++
        tNum /= 1000

    }
    return result.filter { it.isNotEmpty() }.joinToString(separator = " ")
}

fun getStringFromNumToTause(n: Int, numPos: Int): String {
    val imPadRaz = arrayOf("тысяча", "миллион", "миллиард", "триллион")
    val rodPadRaz = arrayOf("тысячи", "миллиона", "миллиарда", "триллиона")
    val rodPadMRaz = arrayOf("тысяч", "миллионов", "миллиардов", "триллионов")
    val result = mutableListOf<String>()
    var thisNum = n
    var iteration = 0
    var isAddTwoDigits = false
    while (thisNum > 0) {
        if (iteration == 0 && thisNum % 100 in 10..19) {
            result.add(0, getStringToNum(thisNum % 100, iteration, numPos))
            iteration += 2
            thisNum /= 100
            isAddTwoDigits = true
        } else {
            result.add(0, getStringToNum(thisNum % 10, iteration, numPos))
            iteration++
            thisNum /= 10
        }
    }

    if (numPos != 0)
        result.add(if (isAddTwoDigits)
            rodPadMRaz[numPos - 1]
        else when (n % 10) {
            1 -> imPadRaz[numPos - 1]
            in 2..5 -> rodPadRaz[numPos - 1]
            else -> rodPadMRaz[numPos - 1]
        })
    return result.filter { it.isNotEmpty() }.joinToString(separator = " ")
}

fun getStringToNum(n: Int, iteration: Int, numPos: Int): String {
    val imPadEd = arrayOf("один", "два", "три", "четыре", "пять", "шесть", "семь", "восемь", "девять", "десять", "одиннадцать", "двенадцать", "тринадцать", "четырнадцать", "пятнадцать", "шестнадцать", "семнадцать", "восемнадцать", "девятнадцать")
    val imPadDes = arrayOf("десять", "двадцать", "тридцать", "сорок", "пятьдесят", "шестьдесят", "семьдесят", "восемьдесят", "девяносто")
    val imPadSot = arrayOf("сто", "двести", "триста", "четыреста", "пятьсот", "шестьсот", "семьсот", "восемьсот", "девятьсот")
    val podPadEd = arrayOf("одна", "две")
    val iterationMas = arrayOf(imPadEd, imPadDes, imPadSot)
    return when (n) {
        0 -> ""
        1 -> if (numPos == 1 && iteration == 0) podPadEd[0] else iterationMas[iteration][n - 1]
        2 -> if (numPos == 1 && iteration == 0) podPadEd[1] else iterationMas[iteration][n - 1]
        in 3..9 -> iterationMas[iteration][n - 1]
        else -> imPadEd[n - 1]
    }
}