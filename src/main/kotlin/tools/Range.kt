package tools

// int

val IntRange.size get() = last - first + 1
fun IntRange.move(offset: Int) = first + offset..last + offset
fun IntRange.hasIntersection(other: IntRange) = first in other || last in other || other.first in this
operator fun IntRange.contains(d: Double) = d >= start && d <= endInclusive
fun rangeMinMax(a: Int, b: Int) = a.coerceAtMost(b)..a.coerceAtLeast(b)
fun intRange(r: ClosedFloatingPointRange<Double>) =
    kotlin.math.ceil(r.start).toInt()..kotlin.math.floor(r.endInclusive).toInt()

// long

val LongRange.size get() = last - first + 1

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
