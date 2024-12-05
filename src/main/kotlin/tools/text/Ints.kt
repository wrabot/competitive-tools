package tools.text

fun String.toInts(delimiter: String = " ") = trim().split(delimiter).map { it.toInt() }
