//вынести основной алгоритм пр.№1 задание №1 в функцию
//1. как обычную функцию
//2. как tailrec-функцию

//вынести проверяемое условие в отдельную single expression функцию

//single expression function
private fun singleCheck(num : Int) = num % 2 != 0

private tailrec fun sumTailrec(number : Int, sum : Int = 0): Int =
    if (number == 0) sum
    else sumTailrec(number / 10, if (singleCheck(number % 10)) sum + number % 10 else sum)

private fun sum(number : Int) : Int {
    var sum = 0
    var loopNumber = number
    while (loopNumber != 0) {
        if (singleCheck(loopNumber % 10)) sum += loopNumber % 10
        loopNumber /= 10
    }
    return sum
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