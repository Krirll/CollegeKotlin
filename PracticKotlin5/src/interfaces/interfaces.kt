package interfaces

import table.DataRow
import table.NullableDataRow
import java.time.LocalDate
//_________________________________________Table_____________________________________________________________
interface Table {
    fun getList() : Iterable<DataRow>
    fun add(dataRow: DataRow)
    fun set(index : Int, dataRow: DataRow)
    fun delete(index: Int)
}

interface Column {
    val name : String
    fun add(nullableDataRow: NullableDataRow)
    fun edit(editableRow : DataRow)
    fun sort(list : List<DataRow>) : List<DataRow>
    fun search(list : List<DataRow>) : List<DataRow>
}

//_________________________________________________Input____________________________________________________
interface RowIndex {
    fun getIndex(message: String, listSize : Int) : Int
}

interface InputLooper {
    fun input(message : String, regex : Regex) : String
}

interface Read {
    fun readString() : String?
}

//________________________________________________Menu Commands_____________________________________________
interface ExecutorCommand {
    fun execute(dataBase: Table)
}

//_____________________________________________Output___________________________________________________________
interface PrintMessage {
    fun printMessage(message : String)
}

interface PrintCommands {
    fun printCommands(commands: List<String>)
}

interface PrintColumns {
    fun printColumns(columns : List<String>)
}

//______________________________________Validator______________________________________________________________
interface StringValidation {
    fun check(regex : Regex, inputString : String?) : Boolean
}

//______________________________________Parsers________________________________________________________________
interface IntParser {
    fun parseToInt(inputString: String) : Int
}

interface DoubleParser {
    fun parseToDouble(inputString: String) : Double
}

interface DateParser {
    fun parseToDate(inputString : String) : LocalDate
}