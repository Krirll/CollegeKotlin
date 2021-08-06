/*
создайте функцию, которая по данным функциям
с параметром типа Int и результатами типа Int возвращает
новую функцию - произведение данных (кол-во исходных функций - любое)
*/
private fun multiply(vararg functions : (Int) -> Int) : (Int) -> Int {
    return { x -> functions.map { it(x) }.fold(1) {res, x -> res * x} }
}

fun main() {
    val function = multiply({x : Int -> x * x}, { x : Int -> x * x * x})
    println(function(3))
}