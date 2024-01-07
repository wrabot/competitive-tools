package tools.geometry

import tools.math.DoubleMatrix

data class Circle(val center: PointD, val radius2: Double) {
    operator fun contains(point: PointD) = (point - center).norm2() <= radius2
}

// Welzl's algorithm
fun smallestCircle(points: List<PointD>, r: List<PointD>): Circle? {
    val point = points.firstOrNull()
    if (point == null || r.size == 3) {
        return when (r.size) {
            0 -> null
            1 -> Circle(r[0], 0.0)
            2 -> Circle((r[0] + r[1]) / 2.0, (r[1] - r[0]).norm2() / 4)
            else -> circumscribedCircle(r[0], r[1], r[2])
        }
    }
    val remaining = points.drop(1)
    val disk = smallestCircle(remaining, r)
    return if (disk != null && point in disk) disk else smallestCircle(remaining, r + point)
}

fun circumscribedCircle(a: PointD, b: PointD, c: PointD): Circle? {
    val div = DoubleMatrix(3).init(
        a.x, a.y, 1.0,
        b.x, b.y, 1.0,
        c.x, c.y, 1.0,
    ).det() * 2
    if (div == 0.0) return null
    val center = PointD(
        (DoubleMatrix(3).init(
            a.norm2(), a.y, 1.0,
            b.norm2(), b.y, 1.0,
            c.norm2(), c.y, 1.0,
        ).det() / div),
        (DoubleMatrix(3).init(
            a.x, a.norm2(), 1.0,
            b.x, b.norm2(), 1.0,
            c.x, c.norm2(), 1.0,
        ).det() / div),
    )
    return Circle(center, (a - center).norm2())
}
