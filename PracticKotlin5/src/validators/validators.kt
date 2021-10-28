package validators

import interfaces.StringValidation

class ValidateString : StringValidation {
    override fun check(regex: Regex, inputString : String?): Boolean =
        inputString?.matches(regex) == true && inputString.isNotEmpty()
}