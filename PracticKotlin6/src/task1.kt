/*
создайте функцию, которая по данным функциям с параметрами
любого типа и результатами типа Int возвращает новую функцию -
произведение данных (кол-во исходных функций любое)
*/
private fun<T> multiply(vararg functions : (T) -> Int) : (T) -> Int {
    return { x -> functions.map { it(x) }.reduce {a, b -> a * b} }
}

fun main() {
    val function = multiply<Double>({(it * it).toInt() },
                                    {(it * it * it).toInt()},
                                    {(it * it * it * it).toInt()})
    println(function(2.0))
}