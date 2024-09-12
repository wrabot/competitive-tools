package tools.read

fun readAllLines() = generateSequence(::readlnOrNull).toList()
