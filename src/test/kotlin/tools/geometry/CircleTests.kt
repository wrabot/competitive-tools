package tools.geometry

import kotlin.test.Test
import kotlin.test.assertEquals

class CircleTests {
    @Test
    fun testSmallestCircle1() {
        val points = listOf(
            4930 to 3841,
            4797 to 2034,
        ).map { PointD(it.first.toDouble(), it.second.toDouble()) }
        assertEquals(Circle(PointD(4863.5, 2937.5), 820734.5), smallestCircle(points, emptyList()))
    }

    @Test
    fun testSmallestCircle2() {
        val points = listOf(
            7344 to 7590,
            1937 to 3559,
            6663 to 8359,
            1053 to 2949,
            2986 to 92,
            8525 to 1684,
            3205 to 7614,
            405 to 2729,
            846 to 8618,
            1537 to 1197
        ).map { PointD(it.first.toDouble(), it.second.toDouble()) }
        assertEquals(Circle(PointD(4567.7294627427655, 5020.576008710945), 2.679272956694962E7), smallestCircle(points, emptyList()))
    }
}