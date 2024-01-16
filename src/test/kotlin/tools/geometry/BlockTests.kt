package tools.geometry

import kotlin.test.Test
import kotlin.test.assertEquals

class BlockTests {
    private val b1 = Block(Point(1, 2, 3), Point(4, 5, 6))
    private val b2 = Block(Point(3, 2, 1), Point(6, 5, 4))
    private val b3 = Block(Point(3, 2, 3), Point(4, 5, 4))
    private val b4 = Block(Point(10, 2, 3), Point(40, 5, 6))

    @Test
    fun testIntersectOk() = assertEquals(b3, b1 intersect b2)

    @Test
    fun testIntersectKo() = assertEquals(null, b1 intersect b4)
}
