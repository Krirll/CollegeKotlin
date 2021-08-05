//сумма нечетных цифр
fun main() {
    print("Write number (must be > 0) -> ")
    val number = readLine()
    if (number != null) {
        var sum = 0
        for (currentChar in number) {
            if ((currentChar - '0') % 2 != 0) sum += currentChar - '0'
        }
        println("Sum = $sum")
    }
}