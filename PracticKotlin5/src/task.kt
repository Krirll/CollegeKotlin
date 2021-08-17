import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException

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
        while (!elem.price.toString().matches("^[1-9]+\\d*".toRegex())) {
            print("Write price of product -> ")
            val price = readLine()
            if (price?.toIntOrNull() != null) elem.price = price.toInt()
        }
        while (!elem.count.toString().matches("^[1-9]+\\d*?".toRegex())) {
            print("Write count of product -> ")
            val count = readLine()
            if (count?.toIntOrNull() != null) elem.count = count.toInt()
        }
        while (elem.dateOfBuy == LocalDate.now()) {
                print("Write date of buy (format dd.mm.yyyy) -> ")
            try {
                val newDate = LocalDate.parse(readLine(),DateTimeFormatter.ofPattern("dd.MM.yyyy"))
                if (newDate != null) elem.dateOfBuy = newDate
            }
            catch (ex : DateTimeParseException) {}
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
                var number = readLine()
                if (number?.toIntOrNull() != null) {
                    if (number.toInt() in 1..objects.size) {
                        number = (number.toInt() - 1).toString()
                        while (!flag) {
                            val lastCommand = fields.count()
                            println("Parameters:")
                            for (i in 0..lastCommand) {
                                if (i == lastCommand) {
                                    println("${i + 1}. exit")
                                }
                                else println("${i + 1}. ${fields[i]}")
                            }
                            print("write a number of parameter you want to edit -> ")
                            val param = readLine()
                            if (param?.toIntOrNull() != null)
                                when (param.toInt()) {
                                    1 -> { //edit product
                                        objects[number.toInt()].product = ""
                                        while (!objects[number.toInt()].product.matches("\\w+".toRegex())) {
                                            print("Write a name of product -> ")
                                            objects[number.toInt()].product = readLine().toString()
                                        }
                                    }
                                    2 -> { //price
                                        objects[number.toInt()].price = 0
                                        while (!objects[number.toInt()].price.toString().matches("^[1-9]+\\d*".toRegex())) {
                                            print("Write price of product -> ")
                                            val price = readLine()
                                            if (price?.toIntOrNull() != null) objects[number.toInt()].price = price.toInt()
                                        }
                                    }
                                    3 -> { //count
                                        objects[number.toInt()].count = 0
                                        while (!objects[number.toInt()].count.toString().matches("^[1-9]+\\d*".toRegex())) {
                                            print("Write count of product -> ")
                                            val count = readLine()
                                            if (count?.toIntOrNull() != null) objects[number.toInt()].count = count.toInt()
                                        }
                                    }
                                    4 -> { //dateOfBuy
                                        objects[number.toInt()].dateOfBuy = LocalDate.now()
                                        while (objects[number.toInt()].dateOfBuy == LocalDate.now()) {
                                            print("Write date of buy (format dd.mm.yyyy) -> ")
                                            try {
                                                val newDate = LocalDate.parse(readLine(), DateTimeFormatter.ofPattern("dd.MM.yyyy"))
                                                if (newDate != null) objects[number.toInt()].dateOfBuy = newDate
                                            }
                                            catch (ex : DateTimeParseException) {}
                                        }
                                    }
                                    lastCommand + 1 -> flag = true //exit from edit function
                                    else -> println("\nIncorrect number of parameter!\n")
                                }
                        }
                    }
                    else print("Incorrect number of element!")
                }
            }
        }
    }
    override fun sort() {
        if (objects.isNotEmpty()) {
            for (i in fields.indices) println("${i + 1}. ${fields[i]}")
            var flag = false
            while (!flag) {
                print("Write a number of sort -> ")
                val sort = readLine()
                if (sort?.toIntOrNull() != null) {
                    when (sort.toInt()) {
                        1 -> {
                            flag = true
                            objects.sortBy { it.product }
                        }
                        2 -> {
                            flag = true
                            objects.sortBy { it.price }
                        }
                        3 -> {
                            flag = true
                            objects.sortBy { it.count }
                        }
                        4 -> {
                            flag = true
                            objects.sortBy { it.dateOfBuy }
                        }
                        else -> println("\nIncorrect number of sort!\n")
                    }
                }
            }
        }
        else println("\nDataBase is empty!\n")
    }
    override fun search() {
        if (objects.isNotEmpty()) {
            for (i in fields.indices) println("${i + 1}. ${fields[i]}")
            var flag = false
            while (!flag) {
                print("Write a number of search -> ")
                val search = readLine()
                if (search?.toIntOrNull() != null) {
                    when (search.toInt()) {
                        1 -> {
                            flag = true
                            println("Write a name of product -> ")
                            val product = readLine()
                            if (product != null) {
                                for (i in objects.indices) {
                                    if (objects[i].product.contains(product))
                                        println("${i + 1}. ${objects[i].toString().removeRange(0..10)}")
                                }
                            }
                        }
                        2 -> {
                            flag = true
                            println("Write price -> ")
                            val price = readLine()
                            if (price != null) {
                                for (i in objects.indices) {
                                    if (objects[i].price == price.toInt())
                                        println("${i + 1}. ${objects[i].toString().removeRange(0..10)}")
                                }
                            }
                        }
                        3 -> {
                            flag = true
                            println("Write count -> ")
                            val count = readLine()
                            if (count != null) {
                                for (i in objects.indices) {
                                    if (objects[i].count == count.toInt())
                                        println("${i + 1}. ${objects[i].toString().removeRange(0..10)}")
                                }
                            }
                        }
                        4 -> {
                            flag = true
                            var date = LocalDate.now()
                            while (date == LocalDate.now()) {
                                println("Write a date of buy (format dd.mm.yyyy) -> ")
                                try {
                                    val newDate = LocalDate.parse(readLine(),
                                        DateTimeFormatter.ofPattern("dd.MM.yyyy"))
                                    if (newDate != null) date = newDate
                                } catch (ex: DateTimeParseException) {
                                }
                            }
                            for (i in objects.indices) {
                                if (objects[i].dateOfBuy == date)
                                    println("${i + 1}. ${objects[i].toString().removeRange(0..10)}"
                                    )
                            }
                        }
                        else -> println("\nIncorrect number of sort!\n")
                    }
                }
            }
        }
        else println("\nDataBase is empty!\n")
    }
    override fun printAll() : Boolean {
        var flag = false
        if (objects.isEmpty()) {
            println("\nDataBase is empty!\n")
            flag = true
        }
        else {
            for (i in objects.indices)
                println("${i + 1}. ${objects[i].toString().removeRange(0..10)}")
        }
        return flag //if list is empty - true, else false
    }
}

data class ElementOfDB(var product: String = "unknown name of product",
                  var price: Int = -1,
                  var count: Int = -1,
                  var dateOfBuy: LocalDate = LocalDate.now())

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