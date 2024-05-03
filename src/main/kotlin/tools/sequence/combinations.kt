package tools.sequence

fun <T> List<T>.combinations(length: Int = size): Sequence<List<T>> =
    if (length <= 0 || isEmpty()) sequenceOf(emptyList()) else
        asSequence().flatMap { first -> minus(first).combinations(length - 1).map { listOf(first) + it } }
