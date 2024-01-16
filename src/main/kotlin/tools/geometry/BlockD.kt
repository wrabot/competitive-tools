package tools.geometry

data class BlockD(val start: PointD, val end: PointD) {
    fun intersect(other: BlockD) =
        (PointD(
            start.x.coerceAtLeast(other.start.x),
            start.y.coerceAtLeast(other.start.y),
            start.z.coerceAtLeast(other.start.z)
        ) to PointD(
            end.x.coerceAtMost(other.end.x),
            end.y.coerceAtMost(other.end.y),
            end.z.coerceAtMost(other.end.z)
        )).takeIf { check(it.first, it.second) }?.run { BlockD(first, second) }

    init {
        require(check(start, end)) { "start not before end $this" }
    }

    private fun check(start: PointD, end: PointD) = (end - start).run { x >= 0 && y >= 0 && z >= 0 }
}
