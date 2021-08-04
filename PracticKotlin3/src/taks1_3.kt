/*
задания №1-3 практической №2 выполнить с использованием функционального
подхода в одно выражение каждое (без учета ввода-вывода)
*/
private fun singleCheck(num : Int) = if (num % 2 != 0) num else 0

private tailrec fun sumTailrec(number : Int, sum : Int = 0): Int = if (number == 0) sum
else sumTailrec(number / 10, sum + singleCheck(number % 10))

private fun sum(number : Int) : Int {
    /*var sum = 0
    var loopNumber = number
    while (loopNumber != 0) {
        sum += singleCheck(loopNumber % 10)
        loopNumber /= 10
    }*/
    return number.toString().split(" ").map { it.toInt() }.sumOf {
        if (it % 2 != 0) it else 0
    }
}

fun main() {
    print("Write number (must be > 0) -> ")
    val number = readLine()
    if (number != null) {
        if (number.toIntOrNull() != null) {
            if (number.toInt() > 0) {
                var result = sum(number.toInt())
                println(if (result == 0) "sum = 0 or was error" else "simple function: $result")
                result = sumTailrec(number.toInt())
                println(if (result == 0) "sum = 0 or was error" else "tailrec function: $result")
            } else println("Number must be more than 0")
        } else println("Number must be an integer value and more than 0")
    }
    else print("value is null")
}