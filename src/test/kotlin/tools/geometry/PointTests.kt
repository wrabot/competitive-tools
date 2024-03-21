package tools.geometry

import kotlin.test.Test
import kotlin.test.assertEquals

class PointTests {
    @Test
    fun testAdd() =  assertEquals(Point(5, 5, 5), Point.Zero + Point.One * 5.0)

    @Test
    fun testParse() = assertEquals(Point(1, 2, 3), "1 2 3".toPoint(" "))

    @Test
    fun testManhattan() = assertEquals(6.0, Point(1, -2, 3).manhattan())

    @Test
    fun testNorm() = assertEquals(14.0, Point(1, -2, 3).norm2())
}
