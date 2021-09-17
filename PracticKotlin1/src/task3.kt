/*
найдите последний символ в первом максимально длинном слове с нечетным числом
символов в строке (в строке указываются только слова
разделенные одним или несколькими пробелами)
*/
//держать число в переменной(если нет слова большего по длине чем первое, то первое максимум)

//если есть, то брать следующее лучшее после первого
//держать в переменной только символ и длину слова
fun main() {
    print("Write some words with spaces -> ")
    val words = readLine()
    if (words != null && words.isNotEmpty()) {
        var flag = false
        var currentIndex = 0
        var currentLengthOfWord = 0
        var saveLength = 0
        var nextLengthOfWord = 0
        var sym = ' '
        while (!flag && currentIndex < words.length) {
            if (words[currentIndex] != ' ' && saveLength == 0)
                currentLengthOfWord += 1
            else {
                if (currentLengthOfWord % 2 != 0) {
                    if (saveLength == 0) {
                        sym = words[currentIndex - 1]
                        saveLength = currentLengthOfWord
                    }
                    while (!flag && currentIndex < words.length) {
                        if (words[currentIndex] == ' ') {
                            if (nextLengthOfWord >= 1)
                                flag = true
                        }
                        else nextLengthOfWord += 1
                        if (!flag) currentIndex += 1
                    }
                    flag = false
                    if (nextLengthOfWord % 2 != 0 && nextLengthOfWord > currentLengthOfWord) {
                        flag = true
                        sym = words[currentIndex - 1]
                    }
                    else nextLengthOfWord = 0
                }
                else {
                    currentLengthOfWord = 0
                    saveLength = 0
                }
            }
            currentIndex += 1
        }
        println("result = ${if (sym == ' ') "nothing" else sym}")
    }
    else {
        println("Error input string! You must write some words with spaces between them")
    }
}