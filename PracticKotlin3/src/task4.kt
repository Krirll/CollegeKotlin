/*
с клавиатуры вводится несколько целых значений через пробел.
Найдите (без учета тех чисел, где соответствующей цифры нет):
побитовое ИЛИ предпоследней цифры всех чисел
*/
fun main() {
    print("Write some integer values with spaces between them -> ")
    println("result = ${readLine()?.split(" ")
                                  ?.map { it.toInt() / 10 % 10 }
                                  ?.filter { it != 0 }
                                  ?.reduce{ a, b -> a or b } ?: "empty result"}")
}