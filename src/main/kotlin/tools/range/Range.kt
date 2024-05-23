package tools.range

val IntRange.size get() = last - start + 1
val LongRange.size get() = last - start + 1
fun IntRange.move(offset: Int) = first + offset..last + offset
fun LongRange.move(offset: Int) = first + offset..last + offset

fun <T : Comparable<T>> ClosedRange<T>.hasIntersection(other: ClosedRange<T>) =
    start in other || endInclusive in other || other.start in this
