/*
реализовать с использованием ООП простейшую консольную базу данных
без красивого интерфейса.
Функции: добавление, удаление, изменение, сортировка, поиск, вывод на экран.
База данных расходов семьи. Поля: товар, стоимость, кол-во, дата.
*/

interface MethodsAndFields {
    val fields : Array<String>
    val commands : Array<String>
    fun add()
    fun delete()
    fun edit()
    fun sort()
    fun search()
    fun printAll()
}

class DB : MethodsAndFields
{
    override val fields: Array<String>
        get() = arrayOf("product", "price", "count", "dateOfBuy")
    override val commands: Array<String>
        get() = arrayOf("add", "delete", "edit", "sort", "search", "print all", "exit")
    private lateinit var objects : MutableList<ElementOfDB>

    override fun add() {
        //TODO: бесконечно принимать пока не будет написано правильно
        val elem = ElementOfDB()
        print("Write a name of product -> ")
        elem.product = readLine() ?: "unknown name of product"
        print("Write price of product -> ")
        elem.price = readLine()?.toInt() ?: -1
        print("Write count of product -> ")
        elem.count = readLine()?.toInt() ?: -1
        print("Write date of buy -> ")
        elem.dateOfBuy = readLine() ?: "unknown date of buy"
    }
    override fun delete() {

    }
    override fun edit() {

    }
    override fun sort() {

    }
    override fun search() {

    }
    override fun printAll() {
        for (elem in objects) println("")
    }
}

class ElementOfDB(var product: String = "unknown name of product",
                  var price: Int = -1,
                  var count: Int = -1,
                  var dateOfBuy: String = "unknown date of buy")

fun main() {
    val dataBase = DB()
    var cmdNum = 0
    while (cmdNum != 7) {
        println("Commands: ")
        for (i in dataBase.commands.indices) {
            println("${i + 1}. ${dataBase.commands[i]}")
        }
        print("write number of command -> ")
        val cmd = readLine()
        if (cmd?.toIntOrNull() != null) {
            cmdNum = cmd.toInt()
            when (cmdNum) {
                1 -> dataBase.add() //add
                2 -> dataBase.delete() //delete
                3 -> { } //edit
                4 -> { } //sort
                5 -> { } //search
                6 -> { } //print
                7 -> println("\nSee you again)")
                else -> println("\nCommand wasn't found. Try again!\n")
            }
        }
        else println("Command wasn't found. Try again!\n")
    }
}