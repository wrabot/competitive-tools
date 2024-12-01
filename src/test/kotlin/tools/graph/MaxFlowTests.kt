package tools.graph

import tools.graph.MaxFlow.Edge
import kotlin.test.Test
import kotlin.test.assertEquals


class MaxFlowTests {
    private val edges = listOf(
        Edge(0, 1, 3),
        Edge(0, 3, 3),
        Edge(1, 2, 4),
        Edge(2, 0, 3),
        Edge(2, 3, 1),
        Edge(2, 4, 2),
        Edge(3, 4, 2),
        Edge(3, 5, 6),
        Edge(4, 1, 1),
        Edge(4, 6, 1),
        Edge(5, 6, 9),
    )

    private val edmondsKarp = MaxFlow(7, edges)

    @Test
    fun testMaxFlow1() {
        assertEquals(5, edmondsKarp.maxFlow(0, 6))
        assertEquals(listOf(2, 3, 2, 0, 1, 1, 0, 4, 0, 1, 4), edges.map { it.flow })
    }

    // test recall of max flow
    @Test
    fun testMaxFlow2() {
        assertEquals(5, edmondsKarp.maxFlow(0, 6))
    }
}
