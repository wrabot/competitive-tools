package tools.geometry

import tools.Point

data class Line(val origin: Point, val vector: Point) {
    // return null or the t of intersection point : origin + vector * t == other.origin + other.vector * u
    infix fun intersectXY(other: Line): Double? {
        val dv = vector.x * other.vector.y - vector.y * other.vector.x
        if (dv == 0.0) return null
        val o = other.origin - origin
        return (o.x * other.vector.y - o.y * other.vector.x) / dv
    }

    operator fun get(t: Double) = origin + vector * t
}
