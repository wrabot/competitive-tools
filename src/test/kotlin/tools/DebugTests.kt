package tools

import java.io.ByteArrayOutputStream
import java.io.PrintStream
import kotlin.test.AfterTest
import kotlin.test.Test
import kotlin.test.assertEquals

class DebugTests {
    private val stderr = System.err
    private val digraph = """
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

    @AfterTest
    fun afterTest() {
        System.setErr(stderr)
    }

    @Test
    fun testGraphWiz() {
        val output = ByteArrayOutputStream()
        System.setErr(PrintStream(output, true))
        val size = 20
        val groupSize = 5
        val graph = (0 until size).flatMap { s ->
            val group = (s / groupSize) * groupSize
            when {
                s == 0 -> (groupSize until size step groupSize).toList()
                s % groupSize == 0 -> (group + 1 until group + groupSize).toList()
                else -> emptyList()
            }.map { Triple(s, it, "$s-$it") }
        }.debugGraph()
        assertEquals(digraph, graph)
        assertEquals(digraph + '\n', output.toString())
    }
}
