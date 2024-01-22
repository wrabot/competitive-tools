package tools.geometry

import kotlin.test.Test
import kotlin.test.assertEquals

class PolygonTests {
    private val polygon = Polygon(
        listOf(
            Point(3, 4),
            Point(5, 11),
            Point(12, 8, 20),
            Point(9, 5),
            Point(5, 6),
        )
    )

    private val rectangle = Polygon(
        listOf(
            Point(3, 4),
            Point(13, 4),
            Point(13, 24),
            Point(3, 24),
        )
    )

    @Test
    fun testArea() = assertEquals(30.0, polygon.area)

    @Test
    fun testPerimeter() {
        assertEquals(26.090056432627563, polygon.perimeter)
        assertEquals(60.0, rectangle.perimeter)
    }

    @Test
    fun testRectangleContains() {
        assertEquals(false, Point(2.99, 9.0) in rectangle)
        assertEquals(false, Point(5.0, 24.01) in rectangle)
        assertEquals(true, Point(8, 5) in rectangle)
        assertEquals(false, Point(2, 5) in rectangle)
    }

    @Test
    fun testPolygonContains() {
        assertEquals(false, Point(12.01, 8.0) in polygon)
        assertEquals(false, Point(5.0, 11.01) in polygon)
        assertEquals(false, Point(3.0, 3.99) in polygon)
        assertEquals(false, Point(2.99, 4.0) in polygon)
        assertEquals(true, Point(3, 4) in polygon)
        assertEquals(true, Point(4, 5) in polygon)
        assertEquals(false, Point(3.0, 5.5) in polygon)
        assertEquals(true, Point(4.0, 5.5) in polygon)
        assertEquals(false, Point(5.0, 5.5) in polygon)
        assertEquals(true, Point(7.0, 5.5) in polygon)
        assertEquals(false, Point(10.0, 5.5) in polygon)
        assertEquals(false, Point(3, 6) in polygon)
        assertEquals(true, Point(4, 6) in polygon)
        assertEquals(true, Point(5, 6) in polygon)
        assertEquals(true, Point(8, 6) in polygon)
        assertEquals(true, Point(9, 6) in polygon)
        assertEquals(true, Point(10, 6) in polygon)
        assertEquals(false, Point(11, 6) in polygon)
    }
}
