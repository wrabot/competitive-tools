package tools.board

enum class Direction8(val xy: Board.XY, val c: Char) {
    East(Board.XY(1, 0), '>'),
    NorthEast(Board.XY(1, -1), '7'),
    North(Board.XY(0, -1), '^'),
    NorthWest(Board.XY(-1, -1), 'F'),
    West(Board.XY(-1, 0), '<'),
    SouthWest(Board.XY(-1, 1), 'L'),
    South(Board.XY(0, 1), 'v'),
    SouthEast(Board.XY(1, 1), 'J');

    // TODO replace values with entries and check without get()
    fun turn(step: Int) = values().let { it[(ordinal + step).mod(it.size)] }

    val left get() = turn(1)
    val right get() = turn(-1)
}
