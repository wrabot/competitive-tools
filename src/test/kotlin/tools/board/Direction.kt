package tools.geometry

import tools.board.Board
import tools.board.Direction4
import tools.board.Direction8.*
import kotlin.test.Test
import kotlin.test.assertEquals

class Direction {
    @Test
    fun testDirection4() {
        assertEquals(Direction4.North, Direction4.East.left)
        assertEquals(Direction4.West, Direction4.North.left)
        assertEquals(Direction4.South, Direction4.West.left)
        assertEquals(Direction4.East, Direction4.South.left)

        assertEquals(Direction4.South, Direction4.East.right)
        assertEquals(Direction4.West, Direction4.South.right)
        assertEquals(Direction4.North, Direction4.West.right)
        assertEquals(Direction4.East, Direction4.North.right)

        assertEquals(Direction4.North, Direction4.East.turn(-3))

        assertEquals(Board.XY(0, -1), Direction4.North.xy)
        assertEquals('^', Direction4.North.c)
    }

    @Test
    fun testDirection8() {
        assertEquals(NorthEast, East.left)
        assertEquals(North, NorthEast.left)
        assertEquals(NorthWest, North.left)
        assertEquals(West, NorthWest.left)
        assertEquals(SouthWest, West.left)
        assertEquals(South, SouthWest.left)
        assertEquals(SouthEast, South.left)
        assertEquals(East, SouthEast.left)

        assertEquals(SouthEast, East.right)
        assertEquals(South, SouthEast.right)
        assertEquals(SouthWest, South.right)
        assertEquals(West, SouthWest.right)
        assertEquals(NorthWest, West.right)
        assertEquals(North, NorthWest.right)
        assertEquals(NorthEast, North.right)
        assertEquals(East, NorthEast.right)

        assertEquals(SouthWest, East.turn(-3))

        assertEquals(Board.XY(-1, -1), NorthWest.xy)
        assertEquals('F', NorthWest.c)    }
}