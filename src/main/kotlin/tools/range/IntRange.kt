package tools.range

val IntRange.size get() = last - first + 1
fun IntRange.move(offset: Int) = first + offset..last + offset
fun IntRange.hasIntersection(other: IntRange) = first in other || last in other || other.first in this
operator fun IntRange.contains(d: Double) = d >= start && d <= endInclusive
fun rangeMinMax(a: Int, b: Int) = a.coerceAtMost(b)..a.coerceAtLeast(b)
fun intRange(r: ClosedFloatingPointRange<Double>) =
    kotlin.math.ceil(r.start).toInt()..kotlin.math.floor(r.endInclusive).toInt()
