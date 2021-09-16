//какие цифры присутствуют в каждом неотрицательном числе(разделены одним пробелом)
//массив из 10 единиц, если встречается цифра то увеличивать элемент массива
fun main() {
    print("Write numbers with spaces -> ")
    val string = readLine()
    if (string != null && string != "") {
        var currentIndex = 0
                          //0  1  2  3  4  5  6  7  8  9
        val array = arrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 0)
        var exist = false
        var countNumbers = 0
        for (int in string) {
            if (!exist && int == string[currentIndex]) {
                exist = true
            }
            if (int == ' ') {
                var a = 0
                if (currentIndex != 0)
                    a = string[currentIndex - 1].code
                if (a in 0..9) {
                    array[a] += 1
                    countNumbers += 1
                }
                exist = false
            }
            currentIndex += 1
        }
        currentIndex = 0
        while (currentIndex < array.size) {
            if (array[currentIndex] == countNumbers) print("$currentIndex ")
            currentIndex += 1
        }
    }
    else println("You must write integer values with spaces!")
}