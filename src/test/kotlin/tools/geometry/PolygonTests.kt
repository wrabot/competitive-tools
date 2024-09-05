package tools.geometry

import tools.Point
import kotlin.test.Test
import kotlin.test.assertEquals

class PolygonTests {
    @Test
    fun testArea() = tests.forEach {
        assertEquals(it.area, Polygon(it.points).area, it.name)
    }

    @Test
    fun testPerimeter() = tests.forEach {
        assertEquals(it.perimeter, Polygon(it.points).perimeter, it.name)
    }

    @Test
    fun testContains() = tests.filterIndexed { index, _ -> index != 1  }.forEach {
        assertEquals(it.containsImage, it.createContainsImage(), it.name)
    }
    
    private data class PolygonTest(
        val name: String,
        val points: List<Point>,
        val perimeter: Double,
        val area: Double,
        val containsImage: String,
    ) {
        fun createContainsImage() = with(Polygon(points)) {
            (yRange.start.toInt() - 1..yRange.endInclusive.toInt() + 1).joinToString("\n") { y ->
                (xRange.start.toInt() - 1..xRange.endInclusive.toInt() + 1).joinToString("") { x ->
                    if (Point(x, y) in this) "*" else "#"
                }
            }
        }
    }

    private val tests = listOf(
        PolygonTest(
            name = "triangle1",
            points = listOf(
                Point(13, 7),
                Point(8, 2),
                Point(18, 2),
            ),
            perimeter = 24.14213562373095,
            area = 25.0,
            containsImage = """
                #############
                #***********#
                ##*********##
                ###*******###
                ####*****####
                #####***#####
                ######*######
                #############
            """.trimIndent(),
        ),
        PolygonTest(
            name = "triangle2",
            points = listOf(
                Point(2, 5),
                Point(5, 2),
                Point(5, 8),
            ),
            perimeter = 14.48528137423857,
            area = 9.0,
            containsImage = """
                ######
                ####*#
                ###**#
                ##***#
                #****#
                ##***#
                ###**#
                ####*#
                ######
            """.trimIndent(),
        ),
        PolygonTest(
            name = "rectangle",
            points = listOf(
                Point(3, 4),
                Point(13, 4),
                Point(13, 24),
                Point(3, 24),
            ),
            perimeter = 60.0,
            area = 200.0,
            containsImage = """
                #############
                #***********#
                #***********#
                #***********#
                #***********#
                #***********#
                #***********#
                #***********#
                #***********#
                #***********#
                #***********#
                #***********#
                #***********#
                #***********#
                #***********#
                #***********#
                #***********#
                #***********#
                #***********#
                #***********#
                #***********#
                #***********#
                #############
            """.trimIndent(),
        ),
        PolygonTest(
            name = "polygon",
            points = listOf(
                Point(3, 4),
                Point(4, 7),
                Point(5, 11),
                Point(12, 8),
                Point(9, 5),
                Point(5, 6),
            ),
            perimeter = 26.095329829133085,
            area = 29.5,
            containsImage = """
                ############
                #*##########
                ##*####*####
                ##*******###
                ##********##
                ###********#
                ###*****####
                ###***######
                ###*########
                ############
            """.trimIndent(),
        ),
    )
}
