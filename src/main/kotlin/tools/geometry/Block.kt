package tools.geometry

data class Block(val start: Point, val end: Point) {
    infix fun intersect(other: Block) =
        (Point(
            start.x.coerceAtLeast(other.start.x),
            start.y.coerceAtLeast(other.start.y),
            start.z.coerceAtLeast(other.start.z)
        ) to Point(
            end.x.coerceAtMost(other.end.x),
            end.y.coerceAtMost(other.end.y),
            end.z.coerceAtMost(other.end.z)
        )).takeIf { check(it.first, it.second) }?.run { Block(first, second) }

    init {
        require(check(start, end)) { "start not before end $this" }
    }

    private fun check(start: Point, end: Point) = (end - start).run { x >= 0 && y >= 0 && z >= 0 }
}
