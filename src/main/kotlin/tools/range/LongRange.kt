package tools.range

fun List<LongRange>.merge() = mutableListOf<LongRange>().also { merge ->
    sortedBy { it.first }.forEach {
        val last = merge.lastOrNull()
        when {
            it.isEmpty() -> Unit
            last == null -> merge.add(it)
            it.first >= last.first && it.first <= last.last + 1 ->
                merge[merge.lastIndex] = last.first..last.last.coerceAtLeast(it.last)

            else -> merge.add(it)
        }
    }
}

fun LongRange.splitWith(other: LongRange) = when {
    first > other.last -> Triple(null, null, this)
    last < other.first -> Triple(this, null, null)
    first >= other.first && last <= other.last -> Triple(null, this, null)
    first >= other.first -> Triple(null, first..other.last, other.last + 1..last)
    last <= other.last -> Triple(first until other.first, other.first..last, null)
    else -> Triple(first until other.first, other, other.last + 1..last)
}
