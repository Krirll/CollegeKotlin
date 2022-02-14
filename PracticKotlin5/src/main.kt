import interfaces.*
import io.*
import parsers.ParserToInt
import table.DataTable
import validators.ValidateString

/*
Реализовать с использованием ООП (SOLID) простейшую консольную базу данных
без красивого интерфейса.
Функции: добавление, удаление, изменение, сортировка, поиск, вывод на экран.
База данных расходов семьи. Поля: товар, стоимость, кол-во, дата.
*/

class Menu (
    private val outputMessage : PrintMessage = Speaker(),
    private val outputCommands : PrintCommands = OutputCommands(),
    private val stringValidation : StringValidation = ValidateString(),
    private val intParser : IntParser = ParserToInt(),
    private val inputString : Read = InputString(),
    private val dataBase : Table = DataTable(),
    private val listCommands: List<Triple<Int, String, ExecutorCommand>>
) {
    fun run() {
        var cmdNum : Int? = 0
        while (cmdNum != null) {
            outputMessage.printMessage("Commands: \n")
            outputCommands.printCommands(listCommands.map { "${it.first + 1}. ${it.second}" })
            outputMessage.printMessage("If you want to exit, you must to write 'exit'!!!\n")
            outputMessage.printMessage("write number of command -> ")
            val cmd = inputString.readString()
            if (cmd != null) {
                if (stringValidation.check("^[1-9]+\\d*".toRegex(), cmd)) {
                    cmdNum = intParser.parseToInt(cmd) - 1
                    listCommands.firstOrNull {
                        it.first == cmdNum
                    }?.third
                        ?.execute(dataBase)
                        ?: println("\nCommand wasn't found. Try again!\n")
                } else {
                    if (stringValidation.check("exit".toRegex(), cmd)) {
                        outputMessage.printMessage("\nSee you again)\n")
                        cmdNum = null
                    } else {
                        outputMessage.printMessage("\nCommand wasn't found. Try again!\n")
                    }
                }
            }
            else {
                outputMessage.printMessage("\nВыход из программы\n")
                cmdNum = null
            }
        }
    }
}

fun main() {
    val listCommands : List<Triple<Int, String, ExecutorCommand>> =
        listOf(Triple(0, "add", InputDataRow()),
                Triple(1, "edit", EditRow()),
                Triple(2 , "delete", DeleteRow()),
                Triple(3, "sort", SortTable()),
                Triple(4 ,"search", SearchRow()),
                Triple(5, "print all", OutputData()))
    val outputMessage : PrintMessage = Speaker()

    val outputCommands : PrintCommands = OutputCommands()
    val stringValidation : StringValidation = ValidateString()
    val intParser : IntParser = ParserToInt()
    val inputString : Read = InputString()
    val dataBase : Table = DataTable()
    Menu(outputMessage,
        outputCommands,
        stringValidation,
        intParser,
        inputString,
        dataBase,
        listCommands).run()
}
