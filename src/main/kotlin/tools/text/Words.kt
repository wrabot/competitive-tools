package tools.text

fun String.toWords() = trim().split(Regex("\\s+"))
