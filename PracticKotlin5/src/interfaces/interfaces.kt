package interfaces

import table.DataRow
import java.time.LocalDate
//_________________________________________Table_____________________________________________________________
interface Headers {
    val headers : List<String>
}

interface Commands {
    val commands : List<Pair<Int,String>>
}

interface Table {
    var list : MutableList<DataRow>
}

interface RowIndex {
    fun getIndex(message: String, listSize : Int) : Int
}

interface InputLooper {
    fun input(message : String, regex : String) : String
}

//________________________________________________Menu Commands_____________________________________________
interface Read {
    fun readString() : String?
}

interface AddDataRow {
    fun addDataRow() : DataRow
}

interface DeleteData {
    fun delete(dataBase : Table)
}

interface EditData {
    fun edit(dataBase: Table)
}

interface SortData {
    fun sortBy(data: MutableList<DataRow>)
}

interface SearchData {
    fun search(data : MutableList<DataRow>)
}

//_____________________________________________Output___________________________________________________________
interface PrintMessage {
    fun printMessage(message : String)
}

interface PrintCommands {
    fun printCommands(commands: List<Pair<Int, String>>)
}

interface PrintData {
    fun printData(data : MutableList<DataRow>)
}

interface PrintHeaders {
    fun printHeaders(headers : List<String>)
}

//______________________________________Validators______________________________________________________________
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
    fun parseToDate(inputString : String?) : LocalDate?
}