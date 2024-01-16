package tools.geometry

import kotlin.test.Test
import kotlin.test.assertEquals

class LineTests {
    private val l1 = Line(Point(1, 2), Point(3, 2))
    private val l2 = Line(Point(6, 2), Point(2, 4))

    @Test
    fun testOperator() {
        assertEquals(Point(4, 4), l1[1.0])
        assertEquals(Point(5.5, 5.0), l1[1.5])
        assertEquals(Point(8.5, 7.0), l1[2.5])
        assertEquals(Point(8.5, 7.0), l2[1.25])
    }

    @Test
    fun testIntersect() = assertEquals(2.5, l1 intersectXY l2)
}
