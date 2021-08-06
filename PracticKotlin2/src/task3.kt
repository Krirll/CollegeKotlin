/*
функции, созданные в задании №1 модифицируйте таким образом,
чтобы условие, по которому происходит отбор, можно было передавать
как аргумент (один из аргументов функции должен быть lambda
со значением по умолчанию - условием, что указано было в вашем варианте)
*/

private tailrec fun sumTailrec(number : Int, sum : Int = 0,
                      check : (Int) -> Boolean = { it % 2 != 0 }): Int =
    if (number == 0) sum
    else sumTailrec(number / 10, if (check(number % 10)) sum + number % 10 else sum, check)

private fun sum(number: Int, check: (Int) -> Boolean = { it % 2 != 0 }) : Int {
    var sum = 0
    var loopNumber = number
    while (loopNumber != 0) {
        if (check(loopNumber % 10)) sum += loopNumber % 10
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
                val lambda = { it : Int ->  it % 2 == 0 }
                var result = sum(number.toInt(), lambda)
                println(if (result == 0) "sum = 0 or was error" else "simple function: $result")
                result = sumTailrec(number.toInt(), check = lambda)
                println(if (result == 0) "sum = 0 or was error" else "tailrec function: $result")
            } else println("Number must be more than 0")
        } else println("Number must be an integer value and more than 0")
    }
    else print("value is null")
}