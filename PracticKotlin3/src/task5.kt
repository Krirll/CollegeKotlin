/*
С клавиатуры вводится информация о студентах: фамилия, имя, оценки.
Выведите на экран информацию о трех лучших студентах по максимальному баллу.
В случае, если у нескольких студентов максимальный балл совпадает,
то выведите большее число студентов (пока не будут выведены все студенты или
не будут полностью исчерпаны студенты с максимальными баллами).
Вывод надо осуществлять в порядке убывания максимального балла, а для одинаковых
максимальных баллов - в алфавитном порядке по фамилии и имени.

*/
fun main() {
//для ввода
// val l2 = generateSequence { readLine() }.takeWhile{it!=""}.map {
// val l = it.split(Regex(",\\s*"));
// Triple(l[0], l[1], l[2].split(Regex("\\s+")).map { it -> it.toInt()!! })
// }
val l2 = listOf(Triple("m", "k", listOf(1, 2, 3)),
                Triple("s", "n", listOf(4, 5, 6)),
                Triple("z", "5", listOf(5, 5, 5)),
                Triple("l", "5", listOf(5, 5, 5)),
                Triple("a", "5", listOf(5, 5, 2)),
                Triple("b", "5", listOf(5, 5, 2)),
                Triple("c", "5", listOf(5, 5, 2)))
    println(
        l2.groupBy(keySelector = { it.third.maxOrNull() }, valueTransform = { it.first + " " + it.second + "\n"})
            .toList()
            .sortedByDescending { it.first }
            .take(3)
            .map { it.second }.joinToString("\n") { it.joinToString("") }
    )
}
