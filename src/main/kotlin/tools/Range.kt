package tools

import kotlin.math.max
import kotlin.math.min

fun IntRange.size() = last - first + 1
fun IntRange.move(offset: Int) = first + offset..last + offset
fun IntRange.hasIntersection(other: IntRange) = first in other || last in other || other.first in this
fun rangeMinMax(a: Int, b: Int) = min(a, b)..max(a, b)
fun intRange(r: ClosedFloatingPointRange<Double>) = r.start.toInt()..r.endInclusive.toInt()
operator fun IntRange.contains(d: Double) = d >= start && d <= endInclusive

fun List<IntRange>.merge() = mutableListOf<IntRange>().also { merge ->
    sortedBy { it.first }.forEach {
        val last = merge.lastOrNull()
        when {
            it.isEmpty() -> Unit
            last == null -> merge.add(it)
            it.first >= last.first && it.first <= last.last + 1 ->
                merge[merge.lastIndex] = last.first..max(last.last, it.last)

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
