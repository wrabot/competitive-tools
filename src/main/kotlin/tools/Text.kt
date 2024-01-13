package tools

fun String.toWords() = trim().split(Regex("\\s+"))
fun String.match(regex: Regex) = regex.matchEntire(this)?.run { groupValues.drop(1) }
