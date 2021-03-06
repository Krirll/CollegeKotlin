package table

import interfaces.*
import io.InputString
import io.LooperInput
import io.Speaker
import parsers.ParserToDate
import parsers.ParserToDouble
import parsers.ParserToInt
import java.time.LocalDate

data class DataRow(
    var product: String,
    var price: Double,
    var count: Int,
    var dateOfBuy: LocalDate
)

data class NullableDataRow(
    var product: String? = null,
    var price: Double? = null,
    var count: Int? = null,
    var dateOfBuy: LocalDate? = null
) {
    fun asDataRow() : DataRow = DataRow(product!!, price!!, count!!, dateOfBuy!!)
}

class ProductColumn(
    private val looperInput : InputLooper = LooperInput(),
    private val outputMessage : PrintMessage = Speaker(),
    private val inputString: Read = InputString(),
    override val name : String = "product"
) : Column {
    override fun add(nullableDataRow: NullableDataRow) {
        nullableDataRow.product = looperInput.input("Write a name of product -> ", "\\w+".toRegex())
    }

    override fun edit(editableRow: DataRow) {
        val product = looperInput.input("Write a name of product -> ", "\\w+".toRegex())
        if (product != null)
            editableRow.product = product
    }

    override fun sort(list : List<DataRow>) : List<DataRow> = list.sortedBy { it.product }

    override fun search(list: List<DataRow>): List<DataRow> {
        outputMessage.printMessage("\nВведите название продукта -> ")
        return list.filter {
            it.product.contains(inputString.readString()!!)
        }
    }


}

class PriceColumn(
    private val looperInput : InputLooper = LooperInput(),
    private val doubleParser : DoubleParser = ParserToDouble(),
    private val outputMessage : PrintMessage = Speaker(),
    private val inputString: Read = InputString(),
    override val name : String = "price"
) : Column {
    override fun add(nullableDataRow: NullableDataRow) {
        val price = looperInput.input("Write price of product -> ", "^[1-9]+\\d*(\\.\\d+)?\$".toRegex())
        if (price != null)
            nullableDataRow.price = doubleParser.parseToDouble(price)
    }

    override fun edit(editableRow: DataRow) {
        val newPrice = looperInput.input("Write price of product -> ", "^[1-9]+\\d*(\\.\\d+)?\$".toRegex())
        if (newPrice != null)
            editableRow.price = doubleParser.parseToDouble(newPrice)
    }

    override fun sort(list: List<DataRow>): List<DataRow> = list.sortedBy { it.price }

    override fun search(list: List<DataRow>): List<DataRow> {
        outputMessage.printMessage("\nВведите цену -> ")
        return list.filter {
            it.price.toString().contains(inputString.readString()!!)
        }
    }


}

class CountColumn(
    private val looperInput : InputLooper = LooperInput(),
    private val intParser : IntParser = ParserToInt(),
    private val outputMessage : PrintMessage = Speaker(),
    private val inputString: Read = InputString(),
    override val name : String = "count"
) : Column{
    override fun add(nullableDataRow: NullableDataRow) {
        val count = looperInput.input("Write count -> ", "^[1-9]+\\d*?".toRegex())
        if (count != null)
            nullableDataRow.count = intParser.parseToInt(count)
    }

    override fun edit(editableRow: DataRow) {
        val newCount = looperInput.input("Write count -> ", "^[1-9]+\\d*?".toRegex())
        if (newCount != null)
            editableRow.count = intParser.parseToInt(newCount)
    }

    override fun sort(list: List<DataRow>): List<DataRow> = list.sortedBy { it.count }

    override fun search(list: List<DataRow>): List<DataRow> {
        outputMessage.printMessage("\nВведите количество -> ")
        return list.filter {
            it.count.toString().contains(inputString.readString()!!)
        }
    }


}

class DateColumn(
    private val dateParser: DateParser = ParserToDate(),
    private val outputMessage: PrintMessage = Speaker(),
    private val inputString: Read = InputString(),
    override val name : String = "date of buy"
) : Column {
    override fun add(nullableDataRow: NullableDataRow) {
        nullableDataRow.dateOfBuy = LocalDate.now()
        while (nullableDataRow.dateOfBuy == LocalDate.now()) {
            outputMessage.printMessage("Write date of buy (format dd.mm.yyyy) -> ")
            nullableDataRow.dateOfBuy = dateParser.parseToDate(inputString.readString())
        }
    }

    override fun edit(editableRow: DataRow) {
        val oldDate = editableRow.dateOfBuy
        var newDate : LocalDate? = oldDate
        while (editableRow.dateOfBuy == oldDate || newDate != null) {
            outputMessage.printMessage("Write date of buy (format dd.mm.yyyy) -> ")
            newDate = dateParser.parseToDate(inputString.readString())
            if (newDate != null)
                editableRow.dateOfBuy
        }
    }

    override fun sort(list: List<DataRow>): List<DataRow> = list.sortedBy { it.dateOfBuy }

    override fun search(list: List<DataRow>): List<DataRow> {
        outputMessage.printMessage("\nВведите дату -> ")
        return list.filter {
            it.dateOfBuy.toString().contains(inputString.readString()!!)
        }
    }


}

class DataTable : Table {
    private val list : MutableList<DataRow> = mutableListOf()
    override fun getList() : Iterable<DataRow> {
        return list.toList()
    }
    override fun add(dataRow: DataRow) {
        this.list.add(dataRow)
    }
    override fun set(index : Int, dataRow: DataRow) {
        this.list[index] = dataRow
    }
    override fun delete(index : Int) {
        this.list.removeAt(index)
    }
}