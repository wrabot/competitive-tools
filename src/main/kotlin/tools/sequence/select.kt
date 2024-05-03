package tools.sequence

fun <T> List<T>.select(length: Int): Sequence<List<T>> = when (length) {
    0 -> sequenceOf(emptyList())
    size -> sequenceOf(this)
    else -> {
        val begin = subList(0, lastIndex)
        val last = last()
        begin.select(length) + begin.select(length - 1).map { it + last }
    }
}
