package tools.graph

import kotlin.test.Test
import kotlin.test.assertEquals


class BronKerboshTests {
    private val graph = arrayOf(
        setOf(1, 2, 3, 4, 5),
        setOf(0, 2, 3, 4, 6),
        setOf(0, 1, 3, 4, 7),
        setOf(0, 1, 2, 4, 8),
        setOf(0, 1, 2, 3, 9),
        setOf(0, 6, 7, 8, 9),
        setOf(1, 5, 7, 8, 9),
        setOf(2, 5, 6, 8, 9),
        setOf(3, 5, 6, 7, 9),
        setOf(4, 5, 6, 7, 8),
    )
    
    @Test
    fun testCliqueWithoutPivot() {
        val cliques = bronKerbosch(graph, false)
        assertEquals(
            listOf(
                setOf(0, 1, 2, 3, 4),
                setOf(0, 5),
                setOf(1, 6),
                setOf(2, 7),
                setOf(3, 8),
                setOf(4, 9),
                setOf(5, 6, 7, 8, 9),
            ), cliques
        )
    }

    @Test
    fun testCliqueWithPivot() {
        val cliques = bronKerbosch(graph, true)
        assertEquals(
            listOf(
                setOf(0, 1, 2, 3, 4),
                setOf(0, 5),
                setOf(6, 1),
                setOf(6, 5, 7, 8, 9),
                setOf(7, 2),
                setOf(8, 3),
                setOf(4, 9),
            ), cliques
        )
    }
}
