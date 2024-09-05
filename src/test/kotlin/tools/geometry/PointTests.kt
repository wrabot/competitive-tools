package tools.geometry

import tools.Point
import tools.toPoint
import kotlin.test.Test
import kotlin.test.assertEquals

class PointTests {
    @Test
    fun testPlus() = assertEquals(Point(5, 7, 9), Point(1, 2, 3) + Point(4, 5, 6))

    @Test
    fun testMinus() = assertEquals(Point(-5, -3, -1), Point(1, 2, 3) - Point(6, 5, 4))

    @Test
    fun testTimesFactor() = assertEquals(Point(5, 10, 15), Point(1, 2, 3) * 5.0)

    @Test
    fun testDivFactor() = assertEquals(Point(0.2, 0.4, 0.6), Point(1, 2, 3) / 5.0)

    @Test
    fun testParse() = assertEquals(Point(1, 2, 3), "1 2 3".toPoint(" "))

    @Test
    fun testManhattan() = assertEquals(6.0, Point(1, -2, 3).manhattan())

    @Test
    fun testScalar() = assertEquals(-4.0, Point(1, 2, 3) * Point(4, 5, -6))

    @Test
    fun testNorm() = assertEquals(14.0, Point(1, -2, 3).norm2())

    @Test
    fun testConstants() = assertEquals(Point(5, 5, 5), Point.Zero * 3.0 + Point.One * 5.0)
}
