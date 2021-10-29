package io

import interfaces.*
import parsers.ParserToInt
import table.*
import validators.ValidateString

class InputString : Read {
    override fun readString() : String? {
        return readLine()
    }
}
//TODO ПРИ ВВОДЕ ПЛОХО ОБРАБАТЫВАЕТСЯ CTRL+D
class LooperInput(
    private val stringValidation : StringValidation = ValidateString(),
    private val outputMessage : PrintMessage = Speaker(),
    private val readString : Read = InputString()
) : InputLooper {
    override fun input(message: String, regex: Regex): String {
        var inputString: String? = null
        while (!stringValidation.check(regex, inputString)) {
            outputMessage.printMessage(message)
            inputString = readString.readString()
        }
        return inputString!!
    }
}

class InputIndex(
    private val outputMessage : PrintMessage = Speaker(),
    private val intParser : IntParser = ParserToInt(),
    private val stringValidation : StringValidation = ValidateString(),
    private val inputString : Read = InputString()
) : RowIndex {
    override fun getIndex(message: String, listSize : Int): Int {
        var indexString : String?
        var indexInt = -1
        while (indexInt == -1) {
            outputMessage.printMessage(message)
            indexString = inputString.readString()
            if (stringValidation.check("^[1-9]+\\d*".toRegex(), indexString)) {
                indexInt = intParser.parseToInt(indexString!!)
                if (indexInt !in 1..listSize)
                    indexInt = -1
            }
            else
                outputMessage.printMessage("\nНеправильный номер строки, попробуйте снова\n")
        }
        return indexInt
    }
}

class DeleteRow(
    private val inputIndex : RowIndex = InputIndex(),
    private val outputMessage : PrintMessage = Speaker(),
    private val outputData : ExecutorCommand = OutputData()
) : ExecutorCommand {
    override fun execute(dataBase : Table) {
        if (dataBase.getList().toList().isNotEmpty()) {
            outputData.execute(dataBase)
            val index = inputIndex.getIndex("Введите строку, которую хотите удалить -> ", dataBase.getList().toList().size)
            dataBase.delete(index - 1)
        }
        else {
            outputMessage.printMessage("\nПустая база данных\n")
        }
    }
}

class InputDataRow(
    private val listColumns: List<Column> = listOf(ProductColumn(), PriceColumn(), CountColumn(), DateColumn())
) : ExecutorCommand {
    override fun execute(dataBase: Table) {
        val nullableDataRow = NullableDataRow()
        listColumns.forEach {
            it.add(nullableDataRow)
        }
        dataBase.add(nullableDataRow.asDataRow())
    }
}

class EditRow(
    private val inputIndex : RowIndex = InputIndex(),
    private val outputMessage : PrintMessage = Speaker(),
    private val outputColumns : PrintColumns = OutputColumns(),
    private val outputData : ExecutorCommand = OutputData(),
    private val listColumns : List<Column> = listOf(ProductColumn(), PriceColumn(), CountColumn(), DateColumn())
) : ExecutorCommand {
    override fun execute(dataBase: Table) {
        if (dataBase.getList().toList().isNotEmpty()) {
            outputData.execute(dataBase)
            val indexRow = inputIndex.getIndex("Введите номер строки, которую хотите редактировать -> ",
                                                dataBase.getList().toList().size)
            outputColumns.printColumns(listColumns.map { it.name })
            val indexColumns = inputIndex.getIndex("Введите номер столбца, который хотите редактировать -> ",
                                                    listColumns.size)
            val currentRow = dataBase.getList().toList()[indexRow - 1]
            listColumns.getOrNull(indexColumns - 1)?.edit(currentRow)
            dataBase.set(indexRow - 1, currentRow)
        }
        else
            outputMessage.printMessage("\nDataBase is empty!")
    }
}

class SortTable(
    private val inputIndex : RowIndex = InputIndex(),
    private val printer: Printer = Printer(),
    private val outputColumns : PrintColumns = OutputColumns(),
    private val listColumns : List<Column> = listOf(ProductColumn(), PriceColumn(), CountColumn(), DateColumn())
) : ExecutorCommand {
    override fun execute(dataBase: Table) {
        outputColumns.printColumns(listColumns.map { it.name })
        val indexColumn = inputIndex.getIndex(
            "Введите номер столбца, по которому хотите сортировать -> ",
            listColumns.size
        )
        val list = dataBase.getList().toList()
        printer.show(
            listColumns[indexColumn - 1].sort(list)
        )
    }
}

class SearchRow(
    private val inputIndex : RowIndex = InputIndex(),
    private val printer: Printer = Printer(),
    private val outputColumns : PrintColumns = OutputColumns(),
    private val listColumns : List<Column> = listOf(ProductColumn(), PriceColumn(), CountColumn(), DateColumn())
) : ExecutorCommand {
    override fun execute(dataBase: Table) {
        outputColumns.printColumns(listColumns.map { it.name })
        val indexColumn = inputIndex.getIndex(
            "Введите столбца, по которому хотите найти -> ",
            listColumns.size
        )
        val list = dataBase.getList().toList()
        printer.show(
            listColumns[indexColumn - 1].search(list)
        )
    }
}

class Speaker : PrintMessage {
    override fun printMessage(message: String) {
        print(message)
    }
}

class OutputColumns(
    private val outputMessage : PrintMessage = Speaker()
) : PrintColumns {
    override fun printColumns(columns: List<String>) {
        outputMessage.printMessage("\nHeaders :\n")
        for (i in columns.indices)
            println("${i + 1}. ${columns[i]}")
        println()
    }

}

class OutputCommands : PrintCommands {
    override fun printCommands(commands: List<String>) {
        commands.forEach{
            println(it)
        }
    }
}

class OutputData(
    private val printer: Printer = Printer()
) : ExecutorCommand {
    override fun execute(dataBase: Table) {
        printer.show(dataBase.getList())
    }
}

class Printer {
    fun show(list : Iterable<DataRow>) {
        if (list.toList().isEmpty())
            println("\nDataBase is empty!\n")
        else
            list.forEachIndexed { index, it -> println("${index + 1}. $it") }
    }
}