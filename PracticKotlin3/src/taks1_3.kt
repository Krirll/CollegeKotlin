/*
задания №1-3 практической №1 выполнить с использованием функционального
подхода в одно выражение каждое (без учета ввода-вывода)
1. сумма нечетных цифр
2. какие цифры присутствуют в каждом неотрицательном числе(разделены одним пробелом)
3. найдите последний символ в первом максимально длинном слове с нечетным числом
   символов в строке (в строке указываются только слова
   разделенные одним или несколькими пробелами)
*/

fun main() {
    print("Write number (must be > 0) -> ")
    println("result №1: ${readLine()?.toList()
                                    ?.map { it.code - 48 }
                                    ?.filter { it % 2 != 0 }
                                    ?.sum() ?: "empty result"}")
    print("Write some integer values with spaces between them -> ")
    println("result №2: ${readLine()?.split(" ")
                                    ?.map { it.toInt() }
                                    ?.map { it ->
                                        generateSequence (it) {it / 10}.takeWhile { it != 0 }
                                                                       .map { it % 10}
                                                                       .toSet()
                                    }?.reduce { a,b -> a intersect  b }
                                     ?.joinToString( " ") ?: "empty result"}")
    print("Write some words with spaces between them -> ")
    println("result №3: ${readLine()?.split(" ")
                                    ?.firstOrNull {it.isNotEmpty() && it.length % 2 != 0}
                                    ?.toString()
                                    ?.last() ?: "empty result"}")
}