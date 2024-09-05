package tools.geometry

import tools.Point
import kotlin.math.abs
import kotlin.math.sign
import kotlin.math.sqrt

data class Polygon(val points: List<Point>) {
    val area: Double
    val perimeter: Double

    init {
        var a = 0.0
        var p = 0.0
        var previous = points.last()
        for (current in points) {
            a += det(current, previous)
            p += (current - previous).run { sqrt(x * x + y * y) }
            previous = current
        }
        area = abs(a) / 2
        perimeter = p
    }

    val xRange = points.minOf { it.x }..points.maxOf { it.x }
    val yRange = points.minOf { it.y }..points.maxOf { it.y }
    operator fun contains(p: Point): Boolean {
        if (p.x !in xRange || p.y !in yRange) return false
        var inside = false
        var p1 = points.last()
        for (p2 in points) {
            if (p == p2) return true
            val d = p2 - p1
            if (d.y == 0.0) {
                if (p.y == p1.y && p.x > p1.x != p.x > p2.x) return true
            } else if (p.y > p1.y != p.y > p2.y) when (det(p - p1, d).sign) {
                0.0 -> return true
                d.y.sign -> inside = !inside
            }
            p1 = p2
        }
        return inside
    }

    private fun det(p1: Point, p2: Point) = p1.x * p2.y - p1.y * p2.x
}
