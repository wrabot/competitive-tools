package tools.sequence

fun <T> List<T>.subLists(): Sequence<List<T>> =
    if (isEmpty()) sequenceOf(emptyList()) else subList(0, lastIndex).subLists().flatMap { listOf(it, it + last()) }
