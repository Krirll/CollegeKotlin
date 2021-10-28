import interfaces.*
import io.*
import parsers.ParserToInt
import table.DataTable
import table.TableCommands
import validators.ValidateString

/*
Реализовать с использованием ООП (SOLID) простейшую консольную базу данных
без красивого интерфейса.
Функции: добавление, удаление, изменение, сортировка, поиск, вывод на экран.
База данных расходов семьи. Поля: товар, стоимость, кол-во, дата.
*/

class Menu {
    private var outputMessage : PrintMessage? = null
    private var outputCommands : PrintCommands? = null
    private var tableCommands : Commands? = null
    private var stringValidation : StringValidation? = null
    private var intParser : IntParser? = null
    private var inputString : Read? = null
    private var dataBase : Table? = null
    private var outputData : PrintData? = null
    private var inputDataRow : AddDataRow? = null
    private var deleteClass : DeleteRow? = null
    private var editClass : EditData? = null
    private var searchClass : SearchData? = null
    private var sortClass : SortData? = null
    fun run() {
        deleteClass = DeleteRow()
        dataBase = DataTable()
        outputData = OutputData()
        outputCommands = OutputCommands()
        outputMessage = Speaker()
        tableCommands = TableCommands()
        stringValidation = ValidateString()
        intParser = ParserToInt()
        inputString = InputString()
        inputDataRow = InputDataRow()
        editClass = EditRow()
        searchClass = SearchRow()
        sortClass = SortTable()
        var cmdNum = 0
        while (cmdNum != tableCommands?.commands?.size!! + 1) {
            outputMessage?.printMessage("Commands: \n")
            outputCommands?.printCommands(tableCommands!!.commands)
            outputMessage?.printMessage("write number of command -> ")
            val cmd = inputString?.readString()
            if (stringValidation?.check("[1-${tableCommands!!.commands.size + 1}]".toRegex(), cmd) == true) {
                cmdNum = intParser?.parseToInt(cmd!!)!!
                when (cmdNum) {
                    1 -> dataBase?.list?.add(inputDataRow?.addDataRow()!!)
                    2 -> editClass?.edit(dataBase!!)
                    3 -> deleteClass?.delete(dataBase!!)
                    4 -> sortClass?.sortBy(dataBase?.list!!)
                    5 -> searchClass?.search(dataBase?.list!!)
                    6 -> outputData?.printData(dataBase?.list!!)
                    tableCommands!!.commands.size + 1 ->
                        outputMessage?.printMessage("\nSee you again)")
                    else ->
                        outputMessage?.printMessage("\nCommand wasn't found. Try again!\n")
                }
            }
            else
                outputMessage?.printMessage("\nCommand wasn't found. Try again!\n")
        }
    }
}

fun main() {
    Menu().run()
}
