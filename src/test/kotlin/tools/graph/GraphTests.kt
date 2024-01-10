package tools.math.tools.graph

import tools.graph.bfs
import tools.graph.dfsLenient
import tools.graph.dfsStrict
import tools.graph.toGraphWiz
import tools.log
import kotlin.test.Test
import kotlin.test.assertEquals


class GraphTests {
    private val size = 100
    private val groupSize = 10
    private val start = 0
    private val fullConnected: (Int) -> List<Int> = { if (it < 99) (0..99).toList() else emptyList() }
    private val tree: (Int) -> List<Int> = {
        val group = (it / groupSize) * groupSize
        when {
            it == 0 -> (groupSize until size step groupSize).toList()
            it % 10 == 0 -> (group + 1..group + 9).toList()
            else -> emptyList()
        }
    }
    private val depthFullResult = ((1 until size).toList() + 0).reversed()
    private val depthTreeResult =
        ((groupSize until size step groupSize).flatMap { group -> ((1..9) + 0).map { it + group } } + 0).reversed()
    private val breathTreeResult =
        ((0 until size step groupSize) + (groupSize until size step groupSize).flatMap { group -> (1..9).map { it + group } })

    @Test
    fun testBfs() {
        val path = mutableListOf<Int>()
        bfs(size, start) {
            path.add(it)
            fullConnected(it)
        }
        assertEquals((0..99).toList(), path)
        path.clear()
        bfs(size, start) {
            path.add(it)
            tree(it)
        }
        assertEquals(breathTreeResult, path)
    }

    @Test
    fun testDfsStrict() {
        val path = mutableListOf<Int>()
        dfsStrict(size, start) {
            path.add(it)
            fullConnected(it)
        }
        assertEquals(depthFullResult, path)
        path.clear()
        dfsStrict(size, start) {
            path.add(it)
            tree(it)
        }
        assertEquals(depthTreeResult, path)
    }

    @Test
    fun testDfsLenient() {
        val path = mutableListOf<Int>()
        dfsLenient(size, start) {
            path.add(it)
            fullConnected(it)
        }
        assertEquals(depthFullResult, path)
        path.clear()
        dfsLenient(size, start) {
            path.add(it)
            tree(it)
        }
        assertEquals(depthTreeResult, path)
    }

    val digraph = """
        digraph {
        0 -> 10 [label="0-10"]
        0 -> 20 [label="0-20"]
        0 -> 30 [label="0-30"]
        0 -> 40 [label="0-40"]
        0 -> 50 [label="0-50"]
        0 -> 60 [label="0-60"]
        0 -> 70 [label="0-70"]
        0 -> 80 [label="0-80"]
        0 -> 90 [label="0-90"]
        10 -> 11 [label="10-11"]
        10 -> 12 [label="10-12"]
        10 -> 13 [label="10-13"]
        10 -> 14 [label="10-14"]
        10 -> 15 [label="10-15"]
        10 -> 16 [label="10-16"]
        10 -> 17 [label="10-17"]
        10 -> 18 [label="10-18"]
        10 -> 19 [label="10-19"]
        20 -> 21 [label="20-21"]
        20 -> 22 [label="20-22"]
        20 -> 23 [label="20-23"]
        20 -> 24 [label="20-24"]
        20 -> 25 [label="20-25"]
        20 -> 26 [label="20-26"]
        20 -> 27 [label="20-27"]
        20 -> 28 [label="20-28"]
        20 -> 29 [label="20-29"]
        }""".trimIndent()

    @Test
    fun testGraphWiz() {
        val graph = (0 until 30).flatMap { s -> tree(s).map { Triple(s, it, "$s-$it") } }.toGraphWiz().log()
        assertEquals(digraph, graph)
    }
}
