package tools.range

import kotlin.math.ceil
import kotlin.math.floor

fun rangeMinMax(a: Int, b: Int) = a.coerceAtMost(b)..a.coerceAtLeast(b)
fun intRange(r: ClosedFloatingPointRange<Double>) = ceil(r.start).toInt()..floor(r.endInclusive).toInt()
operator fun IntRange.contains(d: Double) = d >= start && d <= endInclusive
