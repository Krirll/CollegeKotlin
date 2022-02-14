/*
найдите последний символ в первом максимально длинном слове с нечетным числом
символов в строке (в строке указываются только слова
разделенные одним или несколькими пробелами)
*/

fun main() {
    print("Write numbers with spaces -> ")
    val string = readLine()
    if (string != null && string != "") {
        val array = Array(10) {0}
        val arrayOfExistInt = Array(10) {false}
        var countNumbers = 1
        for (int in string) {
            val charToInt = int.code - 48
            if (int != ' ' && charToInt in 0..9) {
                if (!arrayOfExistInt[charToInt] && array[charToInt] < countNumbers) {
                    arrayOfExistInt[charToInt] = true
                    array[charToInt]++
                }
            }
            else {
                if (int == ' ')
                    countNumbers++
                var i = 0
                while (i < arrayOfExistInt.size) {
                    if (arrayOfExistInt[i]) arrayOfExistInt[i] = false
                    i++
                }
            }
        }
        var i = 0
        while (i < array.size) {
            if (array[i] == countNumbers && countNumbers != 1)
                print("$i ")
            i++
        }
    }
    else println("You must write integer values with spaces!")
}
