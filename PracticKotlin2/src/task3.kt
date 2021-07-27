/*
функции, созданные в задании №1 модифицируйте таким образом,
чтобы условие, по которому происходит отбор, можно было передавать
как аргумент (один из аргументов функции должен быть lambda
со значением по умолчанию - условием, что указано было в вашем варианте)
*/

tailrec fun sumTailre(number : Int, sum : Int = 0,
                      check : (Int) -> Int = { num -> if (num % 2 != 0) num else 0 }): Int = if (number == 0) sum
    else sumTailre(number / 10, sum + check(number % 10))

fun summ(number : Int, check : (Int) -> Int = { num -> if (num % 2 != 0) num else 0 }) : Int {
    var sum = 0
    var loopNumber = number
    while (loopNumber != 0) {
        sum += check(loopNumber % 10)
        loopNumber /= 10
    }
    return sum
}

fun main() {
    print("Write number (must be > 0) -> ")
    val number = readLine()
    if (number != null) {
        try {
            if (number.toInt() > 0) {
                var result = summ(number.toInt())
                println(if (result == 0) "sum = 0 or was error" else "simple function: $result")
                result = sumTailre(number.toInt())
                println(if (result == 0) "sum = 0 or was error" else "tailrec function: $result")
            }
            else println("Number must be > 0")
        }
        catch (ex: NumberFormatException) {
            println("You must write integer value, not string!")
        }
    }
    else print("value is null")
}