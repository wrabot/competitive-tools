package tools.graph

import kotlin.test.Test
import kotlin.test.assertContentEquals

class ConvertersTests {
    private val graph = mapOf(
        'a' to setOf('b', 'c', 'd', 'e', 'f'),
        'b' to setOf('a', 'c', 'd', 'e', 'g'),
        'c' to setOf('a', 'b', 'd', 'e', 'h'),
        'd' to setOf('a', 'b', 'c', 'e', 'i'),
        'e' to setOf('a', 'b', 'c', 'd', 'j'),
        'f' to setOf('a', 'g', 'h', 'i', 'j'),
        'g' to setOf('b', 'f', 'h', 'i', 'j'),
        'h' to setOf('c', 'f', 'g', 'i', 'j'),
        'i' to setOf('d', 'f', 'g', 'h', 'j'),
        'j' to setOf('e', 'f', 'g', 'h', 'i'),
    )

    private val intGraph = arrayOf(
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
    fun testIntGraph() {
        assertContentEquals(intGraph, intGraph(graph.keys.toTypedArray(), graph))
    }
}