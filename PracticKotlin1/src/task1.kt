//сумма нечетных цифр
fun main() {
    print("Write number (must be > 0) -> ")
    val number = readLine()
    if (number != null) {
        var sum = 0
        for (currentChar in number) {
            val int = currentChar - '0'
            if (int % 2 != 0) sum += int
        }
        println("Sum = $sum")
    }
    else println("incorrect number!")
}