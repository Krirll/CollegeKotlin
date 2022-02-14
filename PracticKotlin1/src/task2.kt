//какие цифры присутствуют в каждом неотрицательном числе(разделены одним пробелом)
//массив из 10 единиц, если встречается цифра то увеличивать элемент массива
fun main() {
    print("Write numbers with spaces -> ")
    val string = readLine()
    if (string != null && string != "") {
        var currentIndex = 0
        var saveIndex = 0
        val array = Array(10) {0} //0  1  2  3  4  5  6  7  8  9
        var exit = false
        var countNumbers = 0
        var resultCount = 0
            if (string[currentIndex] != ' ') {
                while (saveIndex < string.length) {
                    if (!exit && string[saveIndex] == string[currentIndex]) {
                        exit = true
                        val index = string[currentIndex] - '0'
                        if (array[index] <= countNumbers) array[index] += 1
                    }
                    if (string[saveIndex] == ' ') {
                        exit = false
                        saveIndex = currentIndex
                        countNumbers++
                    }
                    saveIndex++
                }
                resultCount = ++countNumbers
                countNumbers = 0
                exit = false
            }
        currentIndex = 0
        while (currentIndex < array.size) {
            if (array[currentIndex] ==  resultCount) print("$currentIndex ")
            currentIndex += 1
        }
    }
    else println("You must write integer values with spaces!")
}