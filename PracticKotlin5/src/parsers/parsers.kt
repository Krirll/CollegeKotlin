package parsers

import interfaces.DateParser
import interfaces.DoubleParser
import interfaces.IntParser
import interfaces.PrintMessage
import io.Speaker
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException

class ParserToInt : IntParser {
    override fun parseToInt(inputString: String): Int = inputString.toInt()
}

class ParserToDouble : DoubleParser {
    override fun parseToDouble(inputString: String): Double = inputString.toDouble()
}

class ParserToDate : DateParser {
    private var output : PrintMessage? = null
    override fun parseToDate(inputString: String?): LocalDate? {
        var date : LocalDate? = null
        output = Speaker()
        try {
            val formattedDate = LocalDate.parse(
                inputString,
                DateTimeFormatter.ofPattern("dd.MM.yyyy")
            )
            if (formattedDate != null)
                date = formattedDate
        }
        catch (ex: DateTimeParseException) {
            output?.printMessage("\nНевозможно преобразовать вашу дату, попробуйте снова\n")
        }
        return date
    }
}