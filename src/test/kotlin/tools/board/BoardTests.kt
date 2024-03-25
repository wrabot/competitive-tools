package tools.board

import tools.board.Board.XY
import kotlin.test.Test
import kotlin.test.assertEquals

class BoardTests {
    private val board = """
        1234
        5678
        9ABC
    """.trimIndent().lines().toBoard { it }

    private val subBoard = """
        678
        ABC
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

    private data class Cell(var d: Int) {
        override fun toString() = d.toString()
    }

    fun testCreate() {
        val b = Board(4, 4) { x, y -> (y * 4 + x).toString(16) }
        assertEquals(board.toString(), b.toString())
    }

    @Test
    fun testXY() {
        val xy1 = "3 -7".toXY(" ")
        val xy2 = XY(3, 8)
        assertEquals(10, xy1.manhattan())
        assertEquals(XY(6, 1), xy1 + xy2)
        assertEquals(XY(0, -15), xy1 - xy2)
        assertEquals(XY(9, -21), xy1 * 3)
    }

    @Test
    fun testData() {
        val b = board
        assertEquals(4, b.width)
        assertEquals(3, b.height)
        assertEquals(0..2, b.yRange)
        assertEquals(0..3, b.xRange)
        assertEquals('5', b.getOrNull(0, 1))
        assertEquals(4, b.indexOf(XY(0, 1)))
        assertEquals(9, b.indexOf(XY(1, 2)))
        assertEquals(null, b.indexOf(XY(5, 6)))
        assertEquals(null, b.getOrNull(XY(5, 6)))
        assertEquals(listOf('3', '4', '5'), b.cells.drop(2).take(3))
        assertEquals(listOf(XY(2, 0), XY(3, 0), XY(0, 1)), b.xy.drop(2).take(3))
    }

    @Test
    fun testNeighbors() {
        val b = board
        assertEquals(listOf(XY(2, 0), XY(0, 0), XY(1, 1)), b.neighbors4(XY(1, 0)))
        assertEquals(listOf(XY(1, 1), XY(0, 0), XY(0, 2), XY(1, 0), XY(1, 2)), b.neighbors8(XY(0, 1)))
    }

    @Test
    fun testZone4() {
        val b = zoneIn.lines().toBoard { Cell(it - '0') }
        b.zone4(XY(3, 2)) { b[it].d < 8 }.forEach { b[it].d = 0 }
        assertEquals(zoneOut, b.toString())
    }

    @Test
    fun testToString() {
        assertEquals(subBoard, board.toString(XY(1, 1), XY(3, 2)))
    }
}
