package table

import interfaces.Commands
import interfaces.Headers
import interfaces.Table
import java.time.LocalDate

data class DataRow(var product: String? = null,
                   var price: Double? = null,
                   var count: Int? = null,
                   var dateOfBuy: LocalDate? = null)

data class TableHeaders(
    override val headers: List<String> = listOf("product",
                                                "price",
                                                "count",
                                                "dateOfBuy")) : Headers

data class TableCommands(
    override val commands: List<Pair<Int, String>> = listOf(0 to "add",
                                                            1 to "edit",
                                                            2 to "delete",
                                                            3 to "sort",
                                                            4 to "search",
                                                            5 to "print all")) : Commands

class DataTable : Table {
    override var list : MutableList<DataRow> = mutableListOf()
}