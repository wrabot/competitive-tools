package tools.geometry

data class Polygon(val points: List<Point>) {
    val area: Double
    val perimeter: Double

    init {
        var a = 0.0
        var p = 0.0
        var previous = points.last()
        for (current in points) {
            a += det(current, previous)
            p += (current - previous).run { kotlin.math.sqrt(x * x + y * y) }
            previous = current
        }
        area = kotlin.math.abs(a) / 2
        perimeter = p
    }

    private val xRange = points.minOf { it.x }..points.maxOf { it.x }
    private val yRange = points.minOf { it.y }..points.maxOf { it.y }
    operator fun contains(p: Point): Boolean {
        if (p.x !in xRange || p.y !in yRange) return false
        var inside = false
        var previous = points.last()
        for (current in points) {
            val p1 = if (previous.y <= current.y) previous else current
            val p2 = if (previous.y <= current.y) current else previous
            if (p.y in p1.y..p2.y) when (kotlin.math.sign(det(p - p1, p2 - p1))) {
                0.0 -> return true
                1.0 -> inside = !inside
            }
            previous = current
        }
        return inside
    }

    private fun det(p1: Point, p2: Point) = p1.x * p2.y - p1.y * p2.x
}
