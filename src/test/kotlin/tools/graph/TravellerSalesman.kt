package tools.graph

import kotlin.test.Test
import kotlin.test.assertEquals

class TravellerSalesman {
    @Test
    fun testTravellerSalesman() {
        val r = travellerSalesman(
            arrayOf(
                doubleArrayOf(0.0, 10.0, 15.0, 20.0),
                doubleArrayOf(10.0, 0.0, 25.0, 25.0),
                doubleArrayOf(15.0, 25.0, 0.0, 30.0),
                doubleArrayOf(20.0, 25.0, 30.0, 0.0),
            )
        ).toInt()
        assertEquals(80, r)
    }
}
