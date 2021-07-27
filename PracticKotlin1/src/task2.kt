//какие цифры присутствуют в каждом неотрицательном числе(разделены одним пробелом)
fun main() {
    print("Write numbers with spaces -> ")
    val numbers = readLine()
    if (numbers != null && numbers != "") {
        var currentIndex = -1
        var countNumbers = 0
        var loopFlag = false
        var existsNumbers = ""
        for (i in numbers.indices) { //считаю количество чисел
            if (i != numbers.length - 1) {
                if (numbers[i] != ' ' && numbers[i + 1] == ' ') {
                    countNumbers += 1
                }
            } else if (numbers[i] != ' ') countNumbers += 1
        }
        while (!loopFlag) { //основной цикл
            var count = 0
            currentIndex += 1
            if (numbers[currentIndex] != ' ') {
                var existsFlag = false
                for (next in currentIndex until numbers.length) { //считаю, в скольки числах присутствует текущая цифра
                    if (numbers[next] in '0'..'9') {
                        if (numbers[currentIndex] == numbers[next]) {
                            if (!existsFlag) {
                                count += 1
                                existsFlag = true
                            }
                        }
                    }
                    else existsFlag = false
                }
                if (count == countNumbers) {
                    var flag = false
                    for (c in existsNumbers.indices) {
                        if (existsNumbers[c] == numbers[currentIndex]) flag = true
                    }
                    if (!flag) existsNumbers += numbers[currentIndex] + " "
                }
            }
            else loopFlag = true
        }
        println("Exists: ${if (existsNumbers == "") "nothing" else existsNumbers}")
    }
    else println("You must write integer values with spaces!")
}