package tools.graph

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
            it % groupSize == 0 -> (group + 1 until group + groupSize).toList()
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
    fun testDfs() {
        val path = mutableListOf<Int>()
        dfs(size, start) {
            path.add(it)
            fullConnected(it)
        }
        assertEquals(depthFullResult, path)
        path.clear()
        dfs(size, start) {
            path.add(it)
            tree(it)
        }
        assertEquals(depthTreeResult, path)
    }
}
