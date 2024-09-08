package tools.board

import tools.XY
import tools.graph.zone
import kotlin.test.Test
import kotlin.test.assertEquals

class CharBoardTests {
    private val board = """
        1234
        5678
        9ABC
    """.trimIndent()

    private val zoneIn = """
        2199943210
        3987894921
        9856789892
        8767896789
        9899965678
    """.trimIndent()

    private val zoneOut = """
        2199943210
        3980894921
        9800089892
        8000896789
        9899965678
    """.trimIndent()


    @Test
    fun testData() {
        val b = board.lines().toCharBoard()
        assertEquals(3, b.size)
        assertEquals(4, b[0].size)
        assertEquals('5', b.getOrNull(XY(0, 1)))
        assertEquals('A', b[XY(1, 2)])
        assertEquals('B', b[2][2])
        assertEquals(null, b.getOrNull(XY(5, 6)))
        assertEquals(listOf('6', '7', '8'), b[1].drop(1))
    }

    @Test
    fun testNeighbors() {
        val b = board.lines().toCharBoard()
        assertEquals(listOf(XY(2, 0), XY(0, 0), XY(1, 1)), b.neighbors4(XY(1, 0)))
        assertEquals(listOf(XY(1, 1), XY(0, 0), XY(0, 2), XY(1, 0), XY(1, 2)), b.neighbors8(XY(0, 1)))
    }

    @Test
    fun testZone4() {
        val b = zoneIn.lines().toCharBoard()
        zone(XY(3, 2)) { xy -> b.neighbors4(xy).filter { b[it] - '0' < 8 } }.forEach { b[it] = '0' }
        assertEquals(zoneOut, b.toText())
    }

    @Test
    fun testToText() {
        assertEquals(board, board.lines().toCharBoard().toText())
    }
}
