/*
по числу фибоначчи найти его номер
*/

fun main() {
    print("Write the fibonacci number -> ")
    val number = readLine()
    val result = generateSequence(Pair(1, 1)) {
                        Pair(it.second, it.first + it.second)
                    }.takeWhile { it.first <= number?.toInt()!! }.map { it.first }
                                                                 .indexOfFirst { it == number?.toInt() } + 1
    println("result = ${if (result == 0) "empty result" else result}")
}