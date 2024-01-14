package tools.geometry

data class Point(val x: Int = 0, val y: Int = 0, val z: Int = 0) {
    fun rotateX() = Point(x, -z, y)
    fun rotateY() = Point(z, y, -x)
    fun rotateZ() = Point(-y, x, z)
    operator fun minus(other: Point) = Point(x - other.x, y - other.y, z - other.z)
    operator fun plus(other: Point) = Point(x + other.x, y + other.y, z + other.z)
    operator fun times(factor: Int) = Point(x * factor, y * factor, z * factor)
    fun distance(other: Point) = (this - other).run { kotlin.math.abs(x) + kotlin.math.abs(y) }
}

fun String.toPoint() = split(" ").map { it.toInt() }.run { Point(get(0), get(1), getOrNull(2) ?: 0) }
