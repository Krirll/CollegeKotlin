package io

import interfaces.*
import parsers.ParserToDate
import parsers.ParserToDouble
import parsers.ParserToInt
import table.DataRow
import table.TableHeaders
import validators.ValidateString

class InputString : Read {
    override fun readString() : String? {
        return readLine()
    }
}

class LooperInput : InputLooper {
    private var stringValidation : StringValidation? = null
    private var outputMessage : PrintMessage? = null
    private var inputString : Read? = null
    override fun input(message : String, regex : String) : String {
        inputString = InputString()
        outputMessage = Speaker()
        stringValidation = ValidateString()
        var result : String? = null
        while (stringValidation?.check(regex.toRegex(), result) == false) {
            outputMessage?.printMessage(message)
            result = inputString?.readString()
        }
        return result!!
    }

}

class InputIndex : RowIndex {
    private var outputMessage : PrintMessage? = null
    private var outputData : PrintData? = null
    private var intParser : IntParser? = null
    private var stringValidation : StringValidation? = null
    private var inputString : Read? = null
    override fun getIndex(message: String, listSize : Int): Int {
        outputMessage = Speaker()
        outputData = OutputData()
        intParser = ParserToInt()
        inputString = InputString()
        stringValidation = ValidateString()
        var indexString : String?
        var indexInt = -1
        while (indexInt == -1) {
            outputMessage?.printMessage(message)
            indexString = inputString?.readString()
            if (stringValidation?.check("^[1-9]+\\d*".toRegex(), indexString) == true) {
                indexInt = intParser?.parseToInt(indexString!!)!!
                if (indexInt !in 1..listSize)
                    indexInt = -1
            }
            else
                outputMessage?.printMessage("\nНеправильный номер строки, попробуйте снова\n")
        }
        return indexInt
    }
}

class DeleteRow : DeleteData {
    private var inputIndex : RowIndex? = null
    private var intParser : IntParser? = null
    private var outputMessage : PrintMessage? = null
    private var outputData : PrintData? = null
    override fun delete(dataBase : Table) {
        intParser = ParserToInt()
        inputIndex = InputIndex()
        outputData = OutputData()
        if (dataBase.list.isNotEmpty()) {
            outputData?.printData(dataBase.list)
            val index = inputIndex?.getIndex("Введите строку, которую хотите удалить -> ", dataBase.list.size)
            dataBase.list.removeAt(index!! - 1)
        }
        else {
            outputMessage = Speaker()
            outputMessage?.printMessage("\nПустая база данных\n")
        }
    }
}

class InputDataRow : AddDataRow {
    private var output : PrintMessage? = null
    private var intParser : IntParser? = null
    private var doubleParser : DoubleParser? = null
    private var dateParser : DateParser? = null
    private var inputString : Read? = null
    private var looperInput : InputLooper? = null
    override fun addDataRow(): DataRow {
        val dataRow = DataRow()
        output = Speaker()
        intParser = ParserToInt()
        doubleParser = ParserToDouble()
        dateParser = ParserToDate()
        inputString = InputString()

        looperInput = LooperInput()
        dataRow.product = looperInput?.input("Write a name of product -> ", "\\w+")
        dataRow.price = doubleParser?.parseToDouble(
            looperInput?.input("Write price of product -> ", "^[1-9]+\\d*(\\.\\d+)?\$")!!
        )
        dataRow.count = intParser?.parseToInt(
            looperInput?.input("Write count -> ", "^[1-9]+\\d*?")!!
        )
        while (dataRow.dateOfBuy == null) {
            output?.printMessage("Write date of buy (format dd.mm.yyyy) -> ")
            dataRow.dateOfBuy = dateParser?.parseToDate(inputString?.readString())
        }
        return dataRow
    }
}

class EditRow : EditData {
    private var inputIndex : RowIndex? = null
    private var outputMessage : PrintMessage? = null
    private var outputHeaders : OutputHeaders? = null
    private var outputData : PrintData? = null
    private var intParser : IntParser? = null
    private var doubleParser : DoubleParser? = null
    private var dateParser : DateParser? = null
    private var inputString : Read? = null
    private var tableHeaders : Headers? = null
    private var looperInput : InputLooper? = null
    override fun edit(dataBase: Table) {
        outputMessage = Speaker()
        if (dataBase.list.isNotEmpty()) {
            outputHeaders = OutputHeaders()
            outputData = OutputData()
            tableHeaders = TableHeaders()
            inputString = InputString()
            doubleParser = ParserToDouble()
            dateParser = ParserToDate()
            inputString = InputString()
            intParser = ParserToInt()
            inputIndex = InputIndex()
            looperInput = LooperInput()

            outputData?.printData(dataBase.list)
            val indexRow = inputIndex?.getIndex("Введите номер строки, которую хотите редактировать -> ",
                                                dataBase.list.size)!! - 1
            outputHeaders?.printHeaders(tableHeaders?.headers!!)

            val indexHeader = inputIndex?.getIndex("Введите номер столбца, который хотите редактировать -> ",
                                                    tableHeaders?.headers?.size!!)
            when (indexHeader!!) {
                1 -> dataBase.list[indexRow].product =
                    looperInput?.input("Write a name of product -> ", "\\w+")
                2 -> dataBase.list[indexRow].price = doubleParser?.parseToDouble(
                    looperInput?.input("Write price of product -> ", "^[1-9]+\\d*(\\.\\d+)?\$")!!
                )
                3 -> {
                    dataBase.list[indexRow].count = intParser?.parseToInt(
                        looperInput?.input("Write count -> ", "^[1-9]+\\d*?")!!
                    )
                }
                4 -> {
                    val oldDate = dataBase.list[indexRow].dateOfBuy
                    while (dataBase.list[indexRow].dateOfBuy == oldDate ||
                                dataBase.list[indexRow].dateOfBuy == null) {
                        outputMessage?.printMessage("Write date of buy (format dd.mm.yyyy) -> ")
                        dataBase.list[indexRow].dateOfBuy = dateParser?.parseToDate(inputString?.readString())
                    }
                }
            }
        }
        else
            outputMessage?.printMessage("\nDataBase is empty!")
    }
}

class SortTable : SortData {
    private var inputIndex : RowIndex? = null
    private var looperInput : InputLooper? = null
    private var outputMessage : PrintMessage? = null
    private var outputData : PrintData? = null
    private var inputString : Read? = null
    private var outputHeaders : OutputHeaders? = null
    private var tableHeaders : Headers? = null
    override fun sortBy(data: MutableList<DataRow>) {
        inputIndex = InputIndex()
        outputHeaders = OutputHeaders()
        tableHeaders = TableHeaders()
        outputHeaders?.printHeaders(tableHeaders?.headers!!)
        val indexHeader = inputIndex?.getIndex("Введите столбца, по которому хотите сортировать -> ",
            tableHeaders?.headers?.size!!)
        looperInput = LooperInput()
        outputData = OutputData()
        inputString = InputString()
        when (indexHeader!!) {
            1 -> {
                outputData?.printData(
                    data.sortedBy { it.product } as MutableList<DataRow>
                )
            }
            2 -> {
                outputData?.printData(
                    data.sortedBy { it.price } as MutableList<DataRow>
                )
            }
            3 -> {
                outputData?.printData(
                    data.sortedBy { it.count } as MutableList<DataRow>
                )
            }
            4 -> {
                outputData?.printData(
                    data.sortedBy { it.dateOfBuy } as MutableList<DataRow>
                )
            }
            else -> {
                outputMessage = Speaker()
                outputMessage?.printMessage("\nНеверный номер столбца\n")
            }
        }
    }
}

class SearchRow : SearchData {
    private var inputIndex : RowIndex? = null
    private var looperInput : InputLooper? = null
    private var outputMessage : PrintMessage? = null
    private var outputData : PrintData? = null
    private var inputString : Read? = null
    private var outputHeaders : OutputHeaders? = null
    private var tableHeaders : Headers? = null
    override fun search(data : MutableList<DataRow>) {
        inputIndex = InputIndex()
        outputHeaders = OutputHeaders()
        tableHeaders = TableHeaders()
        outputHeaders?.printHeaders(tableHeaders?.headers!!)
        val indexHeader = inputIndex?.getIndex("Введите столбца, по которому хотите найти -> ",
                    tableHeaders?.headers?.size!!)
        looperInput = LooperInput()
        outputData = OutputData()
        inputString = InputString()
        outputMessage = Speaker()
        when (indexHeader!!) {
            1 -> {
                outputMessage?.printMessage("\nВведите название продукта -> ")
                outputData?.printData(
                    data.filter {
                        it.product!!.contains(inputString?.readString()!!)
                    } as MutableList<DataRow>
                )
            }
            2 -> {
                outputMessage?.printMessage("\nВведите цену -> ")
                outputData?.printData(
                    data.filter {
                        it.price.toString().contains(inputString?.readString()!!)
                    } as MutableList<DataRow>
                )
            }
            3 -> {
                outputMessage?.printMessage("\nВведите кол-во -> ")
                outputData?.printData(
                    data.filter {
                        it.count.toString().contains(inputString?.readString()!!)
                    } as MutableList<DataRow>
                )
            }
            4 -> {
                outputMessage?.printMessage("\nВведите дату -> ")
                outputData?.printData(
                    data.filter {
                        it.dateOfBuy.toString().contains(inputString?.readString()!!)
                    } as MutableList<DataRow>
                )
            }
            else -> {
                outputMessage = Speaker()
                outputMessage?.printMessage("\nНеверный номер столбца\n")
            }
        }
    }

}

class Speaker : PrintMessage {
    override fun printMessage(message: String) {
        print(message)
    }
}

class OutputHeaders : PrintHeaders {
    private var outputMessage : PrintMessage? = null
    override fun printHeaders(headers: List<String>) {
        outputMessage = Speaker()
        outputMessage?.printMessage("\nHeaders :\n")
        for (i in headers.indices)
            println("${i + 1}. ${headers[i]}")
        println()
    }

}

class OutputCommands : PrintCommands {
    override fun printCommands(commands: List<Pair<Int, String>>) {
        for (i in commands) {
            println("${i.first + 1}. ${i.second}")
            if (i.first == 5)
                println("${i.first + 2} exit")
        }
    }
}

class OutputData : PrintData {
    override fun printData(data: MutableList<DataRow>) {
        if (data.isEmpty())
            println("\nDataBase is empty!\n")
        else {
            for (i in data.indices)
                println("\n${i + 1}. ${data[i].toString().removeRange(0..6)}")
        }
    }
}