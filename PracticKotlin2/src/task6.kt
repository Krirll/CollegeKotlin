/*
создайте функцию, которая по данным функциям
с параметром типа Int и результатами типа Int возвращает
новую функцию - произведение данных (кол-во исходных функций - любое)
*/

private fun sum(numbers : List<Int>) : Int {
    var result = 0
    numbers.forEach {
        if (result == 0) result += 1
        result *= it
    }
    return result
}

fun main() {
    print("Write some integer values with spaces between them -> ")
    val data = readLine()
    if (data != null) {
        val listOfInt = data.split(" ").mapNotNull { it.toIntOrNull() }
        println("sum = ${sum(listOfInt)}")
    }
    else println("Error! Incorrect input values")
}