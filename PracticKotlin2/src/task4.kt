//основной алгоритм переместить в функцию, в которую в виде лямбды передавать условие отбора слов

private fun searchWord(words : String, condition : (Int) -> Boolean) : Char {
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
            if (condition(currentLengthOfWord)) {
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
                if (condition(nextLengthOfWord) && nextLengthOfWord > currentLengthOfWord) {
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
    return sym
}

fun main() {
    print("Write some words with spaces -> ")
    val words = readLine()
    if (words != null && words.isNotEmpty()) {
        println("result = ${searchWord(words) {length : Int -> length % 2 != 0 }}")
    }
    else {
        println("Error input string! You must write some words with spaces between them")
    }
}