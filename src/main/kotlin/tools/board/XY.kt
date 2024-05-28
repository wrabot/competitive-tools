package tools.board

data class XY(val x: Int, val y: Int) : Comparable<XY> {
    operator fun minus(other: XY) = XY(x - other.x, y - other.y)
    operator fun plus(other: XY) = XY(x + other.x, y + other.y)
    operator fun times(factor: Int) = XY(x * factor, y * factor)
    fun manhattan() = kotlin.math.abs(x) + kotlin.math.abs(y)
    override fun compareTo(other: XY) = y.compareTo(other.y).let { if (it != 0) it else x.compareTo(other.x) }
}

fun String.toXY(delimiter: String) = split(delimiter).map { it.toInt() }.run { XY(get(0), get(1)) }
