package tools

data class XY(val x: Int, val y: Int) : Comparable<XY> {
    override fun compareTo(other: XY) = y.compareTo(other.y).let { if (it != 0) it else x.compareTo(other.x) }
    operator fun minus(other: XY) = XY(x - other.x, y - other.y)
    operator fun plus(other: XY) = XY(x + other.x, y + other.y)
    operator fun times(factor: Int) = XY(x * factor, y * factor)
    fun manhattan() = kotlin.math.abs(x) + kotlin.math.abs(y)
    fun neighbors4() = xy4dir.map { this + it }
    fun neighbors8() = xy8dir.map { this + it }

    companion object {
        val xy4dir = listOf(XY(1, 0), XY(0, -1), XY(-1, 0), XY(0, 1))
        val xy8dir = xy4dir + listOf(XY(1, -1), XY(-1, -1), XY(-1, 1), XY(1, 1))
    }
}

fun String.toXY(delimiter: String) = split(delimiter).map { it.toInt() }.run { XY(get(0), get(1)) }
