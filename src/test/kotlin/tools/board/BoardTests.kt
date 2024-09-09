package tools.board

import tools.XY
import tools.graph.zone
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

    @Test
    fun testCreate() {
        val lines = zoneIn.lines()
        val b = Board(10, 5) { x, y -> lines[y][x] }
        assertEquals(zoneIn, b.toString())
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
        val b = zoneIn.lines().toBoard { CharCell(it) }
        zone(XY(3, 2)) { xy -> b.neighbors4(xy).filter { b[it].c < '8' } }.forEach { b[it].c = '0' }
        assertEquals(zoneOut, b.toString())
    }

    @Test
    fun testToString() {
        assertEquals(subBoard, board.toString(XY(1, 1), XY(3, 2)))
    }

    @Test
    fun testToBoard() {
        val list = mutableListOf<Pair<XY, Char>>()
        assertEquals(subBoard, subBoard.lines().toBoard { it }.toString())
        assertEquals(subBoard, subBoard.lines().toBoardXY { x, y, c ->
            list.add(XY(x, y) to c)
            c
        }.toString())
        val expectedList = listOf(
            XY(x = 0, y = 0) to '6',
            XY(x = 1, y = 0) to '7',
            XY(x = 2, y = 0) to '8',
            XY(x = 0, y = 1) to 'A',
            XY(x = 1, y = 1) to 'B',
            XY(x = 2, y = 1) to 'C'
        )
        assertEquals(expectedList, list)
    }
}
