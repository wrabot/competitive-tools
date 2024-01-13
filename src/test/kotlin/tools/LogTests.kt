package tools.math.tools

import tools.logGraph
import kotlin.test.Test
import kotlin.test.assertEquals


class LogTests {

    val digraph = """
        digraph {
        0 -> 5 [label="0-5"]
        0 -> 10 [label="0-10"]
        0 -> 15 [label="0-15"]
        5 -> 6 [label="5-6"]
        5 -> 7 [label="5-7"]
        5 -> 8 [label="5-8"]
        5 -> 9 [label="5-9"]
        10 -> 11 [label="10-11"]
        10 -> 12 [label="10-12"]
        10 -> 13 [label="10-13"]
        10 -> 14 [label="10-14"]
        15 -> 16 [label="15-16"]
        15 -> 17 [label="15-17"]
        15 -> 18 [label="15-18"]
        15 -> 19 [label="15-19"]
        }""".trimIndent()

    @Test
    fun testGraphWiz() {
        val size = 20
        val groupSize = 5
        val graph = (0 until size).flatMap { s ->
            val group = (s / groupSize) * groupSize
            when {
                s == 0 -> (groupSize until size step groupSize).toList()
                s % groupSize == 0 -> (group + 1 until group + groupSize).toList()
                else -> emptyList<Int>()
            }.map { Triple(s, it, "$s-$it") }
        }.logGraph()
        assertEquals(digraph, graph)
    }
}
