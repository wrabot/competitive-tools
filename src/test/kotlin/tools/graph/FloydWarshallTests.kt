package tools.graph

import kotlin.test.Test
import kotlin.test.assertEquals


class FloydWarshallTests {
    private val weights = mapOf(
        (0 to 2) to -2.0,
        (1 to 0) to 4.0,
        (1 to 2) to 3.0,
        (2 to 3) to 2.0,
        (3 to 1) to -1.0,
    )

    @Test
    fun testFloydWarshallDist() {
        val floydWarshall = FloydWarshall(4, weights)
        val dist = arrayOf(
            doubleArrayOf(0.0, -1.0, -2.0, 0.0),
            doubleArrayOf(4.0, 0.0, 2.0, 4.0),
            doubleArrayOf(5.0, 1.0, 0.0, 2.0),
            doubleArrayOf(3.0, -1.0, 1.0, 0.0),
        )
        assertEquals(dist.map { it.toList() }, floydWarshall.dist.map { it.toList() })
    }

    @Test
    fun testFloydWarshallPath() {
        val floydWarshall = FloydWarshall(4, weights)
        val paths = listOf(
            (0 to 1) to listOf(2, 3, 1),
            (1 to 3) to listOf(0, 2, 3),
            (2 to 0) to listOf(3, 1, 0),
            (3 to 2) to listOf(1, 0, 2),
        )
        assertEquals(paths.map { it.second }, paths.map { floydWarshall.path(it.first.first, it.first.second) })
    }
}
