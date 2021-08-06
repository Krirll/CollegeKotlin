/*
создайте функцию, которая реализует алгоритм второго задания
первой практической, в которую все числа передаются как аргумент
функции. Например f(123,25,222)
*/

fun countSameNumbers(vararg numbers : String) : String {
    var existsNumbers = ""
    for (sym in numbers[0]) {
        var currentCount = 0
        for (nextNum in numbers) {
            if (nextNum.contains(sym)) currentCount += 1
        }
        if (currentCount == numbers.count()) {
            if (!existsNumbers.contains(sym)) existsNumbers += "$sym "
        }
    }
    return if (existsNumbers == "") "nothing" else existsNumbers
}

fun main() {
    print("Write numbers with spaces -> ")
    val numbers = readLine()
    if (numbers != null) {
        println("Exists: ${countSameNumbers(*numbers.split(" ").toTypedArray())}")
    }
}