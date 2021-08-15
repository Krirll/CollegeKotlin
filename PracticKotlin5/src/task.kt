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
    fun printAll() : Boolean
}

class DB : MethodsAndFields {
    override val fields: Array<String> = arrayOf("product", "price", "count", "dateOfBuy")
    override val commands: Array<String> = arrayOf("add", "delete", "edit", "sort", "search", "print all", "exit")
    private var objects : MutableList<ElementOfDB> = mutableListOf()

    override fun add() {
        val elem = ElementOfDB()
        while (!elem.product.matches("\\w+".toRegex())) {
            print("Write a name of product -> ")
            elem.product = readLine().toString()
        }
        while (!elem.price.toString().matches("\\d+".toRegex()) && elem.price != 0) {
            print("Write price of product -> ")
            val price = readLine()
            if (price?.toIntOrNull() != null) elem.price = price.toInt()
        }
        while (!elem.count.toString().matches("\\d+".toRegex()) && elem.price != 0) {
            print("Write count of product -> ")
            val count = readLine()
            if (count?.toIntOrNull() != null) elem.count = count.toInt()
        }
        while (!elem.dateOfBuy.matches("[0-3]?[1-9]\\.[0-1]?[1-9]\\.20\\d\\d".toRegex())) {
            print("Write date of buy (format dd.mm.yyyy or d.m.yyyy) -> ")
            elem.dateOfBuy = readLine().toString()
        }
        objects.add(elem)
    }
    override fun delete() {
        if (!printAll()) {
            var flag = false
            while (!flag) {
                print("Write a number of element you want to delete -> ")
                val number = readLine()
                if (number?.toIntOrNull() != null) {
                    if (number.toInt() in 1..objects.size) {
                        objects.removeAt(number.toInt() - 1)
                        flag = true
                    }
                    else println("Incorrect number of element!")
                }
            }
        }
    }
    override fun edit() {
        if (!printAll()) {
            var flag = false
            while (!flag) {
                print("Write a number of element you want to edit -> ")
                val number = readLine()
                if (number?.toIntOrNull() != null) {
                    if (number.toInt() in 1..objects.size) {
                        while (!flag) {
                            print("write a number of parameter you want to edit -> ")
                            val param = readLine()
                            if (param?.toIntOrNull() != null)
                                when (param.toInt()) {
                                    //TODO: скопировать проверки ввода из add
                                    1 -> {}
                                    2 -> {}
                                    3 -> {}
                                    4 -> {}
                                    else -> println("Incorrect number of parameter!")
                                }
                            flag = true
                        }
                    }
                    else print("Incorrect number of element!")
                }
            }
        }
    }
    override fun sort() {

    }
    override fun search() {

    }
    override fun printAll() : Boolean {
        var flag = false
        if (objects.isEmpty()) {
            println("\nDataBase is empty!\n")
            flag = true
        }
        else {
            for (i in objects.indices)
                println("${i + 1}. product: ${objects[i].product}; price: ${objects[i].price}; count: ${objects[i].count}; date of buy: ${objects[i].dateOfBuy}")
        }
        return flag //if list is empty - true, else false
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
                1 -> dataBase.add()
                2 -> dataBase.delete()
                3 -> dataBase.edit()
                4 -> dataBase.sort()
                5 -> dataBase.search()
                6 -> dataBase.printAll()
                7 -> println("\nSee you again)")
                else -> print("\nCommand wasn't found. Try again!\n")
            }
        }
        else print("\nCommand wasn't found. Try again!\n")
    }
}