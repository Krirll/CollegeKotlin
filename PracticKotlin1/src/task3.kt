/*
найдите последний символ в первом максимально длинном слове с нечетным числом
символов в строке (в строке указываются только слова
разделенные одним или несколькими пробелами)
*/

fun main() {
    print("Write some words with spaces -> ")
    println(readLine()?.split(" ")
                      ?.firstOrNull { it.isNotEmpty() && it.length % 2 != 0 }
                      ?.toString()
                      ?.last() ?: "empty result")
}