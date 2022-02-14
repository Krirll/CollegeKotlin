import java.time.LocalDate

/*
мои варианты задания
    val n = 2
    println(n%4+1)   //3
    println(n/4%4+1) //1
    println(n%5+1)   //3

1. fun insert(elem : T, n : Int): Boolean вставляет элемент на позицию n
(нумерация идет с единицы) с начала списка;
2. fun delete() : Boolean удаляет элемент из начала списка;
3. fun print() : Unit печатает последний элемент списка;

Кроме того должна быть реализована функция eraseAll, которая очищает весь список.
Список должен быть реализован в виде класса.
*/

class List<T> (private val list : MutableList<T> = mutableListOf()) {

    fun insert(elem : T, n : Int) =
        if (n - 1 in 0..list.size) {
            list.add(n - 1, elem)
            true
        } else
            false

    fun delete() =
        if (list.isNotEmpty()) {
            list.removeAt(0)
            true
        } else
            false

    fun print() = if (list.isNotEmpty()) println(list.last()) else println("List is empty")

    fun eraseAll() = list.clear()
}

fun main() {
    val list = List<String>(mutableListOf())
    println(list.insert("some", 1))
    println(list.insert(LocalDate.now().toString(), 1))
    list.insert((10).toString(), 3)
    list.insert("new", 2)
    list.delete()
    list.print()
    list.eraseAll()
    list.print()
}