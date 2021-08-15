/*
программа проверки правильности оператора присваивания,
в правой части которого допустимы операции сложения, вычитания,
умножения, деления, переменные, целые и вещественные числа (включая пользовательскую форму)
*/

fun checkMatches(vararg array : String) {
    for (i in 0 until array.count()) {
        println("${i + 1}${if (Regex("(?=[^+-/*]*[+-/*])(\\s*[A-Za-z_]+\\w*\\s*:=\\s*([A-Za-z_]+\\w*|\\d+|\\d+(?=\\.)\\.\\d+)\\s*[+-/*]\\s*([A-Za-z_]+\\w*|\\d+|\\d+(?=\\.)\\.\\d+)\\s*;$)|(\\s*([A-Za-z_]+\\w*)\\s*:=\\s*([A-Za-z_]+\\w*|\\d+|\\d+(?=\\.)\\.\\d+)\\s*;$)").matches(array[i]))". +" 
                            else ". -"}")
    }
}

fun main() {
    val a = arrayOf("a := 1;",
                    "a := b;",
                    "a := 1 + b;",
                    "a := b + 1;",
                    "a:=10.0;",
                    "a := 10.0+b;",
                    "a := b+10.0;",
                    "a123_:=1*1    ;",
                    "_adfg:=2.235   /   2.125;",
                    "     f4f1   :=    3.000   -   b   ;",
                    "_4 := 100;",
                    " := 10;",
                    " a := ;",
                    "10 := 10",
                    "10.00 := 10.000;")
    checkMatches(*a)
}