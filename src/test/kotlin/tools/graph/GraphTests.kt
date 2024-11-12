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

    @Test
    fun testBfs() {
        val path = mutableListOf<Int>()

        bfs(size, start) {
            path.add(it)
            fullConnected(it)
        }
        val breathFullResult = (0..99).toList()
        assertEquals(breathFullResult, path)

        path.clear()

        bfs(size, start) {
            path.add(it)
            tree(it)
        }
        val breathTreeResult = (0 until size step groupSize) +
                (groupSize until size step groupSize).flatMap { group -> (1..9).map { it + group } }
        assertEquals(breathTreeResult, path)
    }

    @Test
    fun testDfs() {
        val path = mutableListOf<Int>()

        dfs(size, start) {
            path.add(it)
            fullConnected(it)
        }
        val depthFullResult = ((1 until size).toList() + 0).reversed()
        assertEquals(depthFullResult, path)

        path.clear()

        dfs(size, start) {
            path.add(it)
            tree(it)
        }
        val depthTreeResult = ((groupSize until size step groupSize).flatMap { group -> ((1..9) + 0).map { it + group } } + 0)
            .reversed()
        assertEquals(depthTreeResult, path)
    }

    @Test
    fun testMCS() {
        val path = mutableListOf<Int>()

        mcs(size, start) {
            path.add(it)
            fullConnected(it)
        }
        val mcsFullResult = (0..99).toList()
        assertEquals(mcsFullResult, path)

        path.clear()

        mcs(size, start) {
            path.add(it)
            tree(it)
        }
        val mcsTreeResult = listOf(0) + (10..99).toList() + (1..9).toList()
        assertEquals(mcsTreeResult, path)
    }
}
