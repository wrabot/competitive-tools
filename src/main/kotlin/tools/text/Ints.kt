package tools.text

fun String.toInts() = trim().split(" ").map { it.toInt() }
