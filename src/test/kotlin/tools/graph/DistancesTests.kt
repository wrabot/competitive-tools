package tools.graph

import tools.graph.EdmondsKarp.Edge
import kotlin.test.Test
import kotlin.test.assertEquals


class DistancesTests {
    private val edges = listOf(
        Edge(0, 1, 85),
        Edge(0, 2, 217),
        Edge(0, 4, 173),
        Edge(1, 5, 80),
        Edge(2, 6, 186),
        Edge(2, 7, 103),
        Edge(3, 7, 183),
        Edge(4, 9, 502),
        Edge(5, 8, 250),
        Edge(7, 9, 167),
        Edge(8, 9, 84),
    )

    private val neighbors = (edges + edges.map { Edge(it.destination, it.source, it.capacity) }).groupBy { it.source }
        .mapValues { entry -> entry.value.associate { it.destination to it.capacity.toDouble() } }

    @Test
    fun testDistances() {
        val distances = distances(10, 0) { neighbors[it].orEmpty() }
        assertEquals(listOf(0.0, 85.0, 217.0, 503.0, 173.0, 165.0, 403.0, 320.0, 415.0, 487.0), distances.toList())
    }
}
