import java.lang.NumberFormatException

/*
создайте функцию, которая по данным функциям
с параметром типа Int и результатами типа Int возвращает
новую функцию - произведение данных (кол-во исходных функций - любое)
*/

fun sum(numbers : List<Int>) : Int {
    var result = 1
    numbers.forEach {
        result *= it
    }
    return result
}

fun main() {
    print("Write some integer values with spaces between them -> ")
    val data = readLine()
    if (data != null) {
        try {
            val listOfInt = data.split(" ").map { it.toInt() }
            println("sum = ${sum(listOfInt)}")
        }
        catch (ex : NumberFormatException) {
            println("Yout must write integer values with spaces between them!")
        }
    }
    else {
        println("Error! Incorrect input values")
    }
}