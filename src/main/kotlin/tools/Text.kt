package tools

fun String.toWords() = split(Regex("\\s+"))
fun String.match(regex: Regex) = regex.matchEntire(this)?.run { groupValues.drop(1) }
