package tools.graph

import kotlin.test.Test
import kotlin.test.assertContains
import kotlin.test.assertContentEquals
import kotlin.test.assertEquals


class FloydWarshallTests {
    @Test
    fun testFloydWarshall() {
        val floydWarshall = FloydWarshall(
            4,
            mapOf(
                (0 to 2) to -2.0,
                (1 to 0) to 4.0,
                (1 to 2) to 3.0,
                (2 to 3) to 2.0,
                (3 to 1) to -1.0,
            )
        )
        val dist = arrayOf(
            doubleArrayOf(0.0, -1.0, -2.0, 0.0),
            doubleArrayOf(4.0, 0.0, 2.0, 4.0),
            doubleArrayOf(5.0, 1.0, 0.0, 2.0),
            doubleArrayOf(3.0, -1.0, 1.0, 0.0),
        )
        assertEquals(dist.map { it.toList() }, floydWarshall.dist.map { it.toList() })
    }
}
