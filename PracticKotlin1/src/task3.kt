/*
найдите последний символ в первом максимально длинном слове с нечетным числом
символов в строке (в строке указываются только слова
разделенные одним или несколькими пробелами)
*/

fun main() {
    print("Write some words with spaces -> ")
    val words = readLine()
    if (words != null && words.isNotEmpty()) {
        var flag = false
        var currentIndex = 0
        var lengthOfWord = 0
        var result = ""
        while (!flag && currentIndex < words.length) {
            if (words[currentIndex] != ' ') lengthOfWord += 1
            else {
                if (lengthOfWord % 2 != 0) {
                    result += "${words[currentIndex - 2]}"
                    flag = true
                }
                else lengthOfWord = 0
            }
            currentIndex += 1
        }
        println("result = ${if (result == "") "nothing" else result}")
    }
    else {
        println("Error input string! You must write some words with spaces between them")
    }
}