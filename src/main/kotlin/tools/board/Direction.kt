package tools.board

enum class Direction(val delta: Point, val c: Char, val isDiagonal: Boolean = false) {
    North(Point(0, -1), '^'),
    South(Point(0, 1), 'v'),
    East(Point(1, 0), '>'),
    West(Point(-1, 0), '<'),
    NorthWest(Point(-1, -1), 'F', true),
    NorthEast(Point(1, -1), '7', true),
    SouthWest(Point(-1, 1), 'L', true),
    SouthEast(Point(1, 1), 'J', true),
}

// TODo replace values with entries when kotlin version will permit it
val directions4 = Direction.values().filterNot { it.isDiagonal }.map { it.delta }
val directions8 = Direction.values().map { it.delta }
