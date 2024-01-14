package tools.board

enum class Direction(val delta: Board.XY, val c: Char, val isDiagonal: Boolean = false) {
    North(Board.XY(0, -1), '^'),
    South(Board.XY(0, 1), 'v'),
    East(Board.XY(1, 0), '>'),
    West(Board.XY(-1, 0), '<'),
    NorthWest(Board.XY(-1, -1), 'F', true),
    NorthEast(Board.XY(1, -1), '7', true),
    SouthWest(Board.XY(-1, 1), 'L', true),
    SouthEast(Board.XY(1, 1), 'J', true),
}

val directions4 = Direction.values().filterNot { it.isDiagonal }
