package tools

fun <T> List<T>.combinations(length: Int = size): List<List<T>> = if (length <= 0 || isEmpty()) listOf(emptyList()) else
    flatMap { first -> minus(first).combinations(length - 1).map { listOf(first) + it } }

fun <T> enumerate(base: Int, length: Int, prefix: List<Int> = emptyList(), block: (List<Int>) -> T): Sequence<T> =
    if (length <= 0) sequenceOf(block(prefix)) else (0 until base).asSequence().flatMap {
        enumerate(base, length - 1, prefix + it, block)
    }

fun <T> List<T>.subLists(): Sequence<List<T>> =
    if (isEmpty()) sequenceOf(emptyList()) else subList(0, lastIndex).subLists().flatMap { listOf(it, it + last()) }

fun <T> List<T>.subLists(prefix: List<T> = emptyList(), block: (List<T>) -> Unit) {
    if (isEmpty()) block(prefix) else subList(1, size).let {
        it.subLists(prefix, block)
        it.subLists(prefix + first(), block)
    }
}

fun <T> List<T>.select(count: Int): Sequence<List<T>> = when (count) {
    0 -> sequenceOf(emptyList())
    size -> sequenceOf(this)
    else -> {
        val begin = subList(0, lastIndex)
        val last = last()
        begin.select(count) + begin.select(count - 1).map { it + last }
    }
}
