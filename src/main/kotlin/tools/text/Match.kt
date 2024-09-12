package tools.text

fun String.match(regex: Regex) = regex.matchEntire(this)?.run { groupValues.drop(1) }
