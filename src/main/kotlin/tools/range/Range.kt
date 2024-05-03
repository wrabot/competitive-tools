package tools.range

val IntRange.size get() = endInclusive - start + 1
val LongRange.size get() = endInclusive - start + 1
fun <T : Comparable<T>> ClosedRange<T>.hasIntersection(other: ClosedRange<T>) =
    start in other || endInclusive in other || other.start in this
