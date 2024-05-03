package tools.sequence

fun <T> List<T>.enumerate(length: Int, prefix: List<T> = emptyList()): Sequence<List<T>> =
    if (length <= 0) sequenceOf(prefix) else asSequence().flatMap { enumerate(length - 1, prefix + it) }

