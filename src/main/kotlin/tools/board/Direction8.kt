package tools.board

import tools.XY

enum class Direction8(val xy: XY, val c: Char) {
    East(XY(1, 0), '>'),
    NorthEast(XY(1, -1), '7'),
    North(XY(0, -1), '^'),
    NorthWest(XY(-1, -1), 'F'),
    West(XY(-1, 0), '<'),
    SouthWest(XY(-1, 1), 'L'),
    South(XY(0, 1), 'v'),
    SouthEast(XY(1, 1), 'J');

    // TODO replace values with entries and check without get()
    fun turn(step: Int) = values().let { it[(ordinal + step).mod(it.size)] }

    val left get() = turn(1)
    val right get() = turn(-1)
}
