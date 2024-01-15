package tools.geometry

import tools.board.Board.XY
import tools.board.toBoard
import tools.board.toXY
import kotlin.test.Test
import kotlin.test.assertEquals

class Board {
    private val board = """
        1234
        5678
        9ABC
    """.trimIndent().lines()

    private val subboard = """
        678
        ABC
    """.trimIndent()

    @Test
    fun testToXY() {
        val xy1 = "3 -7".toXY()
        val xy2 = XY(3, 8)
        assertEquals(10, xy1.manhattan())
        assertEquals(XY(6, 1), xy1 + xy2)
        assertEquals(XY(0, -15), xy1 - xy2)
        assertEquals(XY(9, -21), xy1 * 3)
    }

    @Test
    fun testToData() {
        val b = board.toBoard { it }
        assertEquals(4, b.width)
        assertEquals(3, b.height)
        assertEquals(0..2, b.yRange)
        assertEquals(0..3, b.xRange)
        assertEquals('5', b.getOrNull(0, 1))
        assertEquals(null, b.getOrNull(XY(5, 6)))
        assertEquals(listOf('3', '4', '5'), b.cells.drop(2).take(3))
        assertEquals(listOf(XY(2, 0), XY(3, 0), XY(0, 1)), b.xy.drop(2).take(3))
    }

    @Test
    fun testToNeighbors() {
        val b = board.toBoard { it }
        assertEquals(listOf(XY(2, 0), XY(0, 0), XY(1, 1)), b.neighbors4(XY(1, 0)))
        assertEquals(listOf(XY(1, 1), XY(0, 0), XY(0, 2), XY(1, 0), XY(1, 2)), b.neighbors8(XY(0, 1)))
        assertEquals(emptyList(), b.neighbors8(XY(5, 6)))
    }

    @Test
    fun testToString() {
        assertEquals(subboard, board.toBoard { it }.toString(XY(1, 1), XY(3, 2)))
    }
}
