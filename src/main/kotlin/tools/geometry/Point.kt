package tools.geometry

data class Point(val x: Double = 0.0, val y: Double = 0.0, val z: Double = 0.0) {
    constructor(x: Int = 0, y: Int = 0, z: Int = 0) : this(x.toDouble(), y.toDouble(), z.toDouble())

    operator fun minus(other: Point) = Point(x - other.x, y - other.y, z - other.z)
    operator fun plus(other: Point) = Point(x + other.x, y + other.y, z + other.z)
    operator fun times(factor: Double) = Point(x * factor, y * factor, z * factor)
    fun manhattan() = kotlin.math.abs(x) + kotlin.math.abs(y)
    fun norm2() = x * x + y * y
}

val Origin = Point(0.0, 0.0, 0.0)

fun String.toPoint() = split(" ").map { it.toDouble() }.run { Point(get(0), get(1), getOrNull(2) ?: 0.0) }
