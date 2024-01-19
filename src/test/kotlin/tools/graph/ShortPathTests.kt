package tools.graph

import tools.graph.EdmondsKarp.Edge
import kotlin.test.Test
import kotlin.test.assertEquals


class ShortPathTests {
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
        .mapValues { entry -> entry.value.associate { it.destination to it.capacity } }

    @Test
    fun testShortPath() {
        val path = shortPath(
            0,
            9,
            cost = { a, b -> neighbors[a].orEmpty()[b]?.toDouble() ?: Double.POSITIVE_INFINITY }
        ) { s -> neighbors[s].orEmpty().keys.toList() }
        assertEquals(listOf(0, 2, 7, 9), path)
        assertEquals(487, path.zipWithNext().sumOf { neighbors[it.first]!![it.second]!! })
    }
}
